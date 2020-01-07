package com.example.kodein.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.kodein.R
import com.example.kodein.base.BaseViewModel
import com.example.kodein.model.Post
import com.example.kodein.model.PostDao
import com.example.kodein.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao): BaseViewModel(){
    @Inject
    lateinit var retrofit: Retrofit
    val postListAdapter: PostListAdapter = PostListAdapter()
    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = Observable.fromCallable { postDao.all }
            .concatMap {
                    dbPostList ->
                if(dbPostList.isEmpty()) {
                    retrofit.create(PostApi::class.java).getPosts().concatMap { apiPostList ->
                            postDao.insertAll(*apiPostList.getList.toTypedArray())
                            Observable.just(apiPostList.getList)
                    }
                }
                else {
                    Observable.just(dbPostList)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { t -> onRetrievePostListError(t) }
            )
    }
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE

    }

    private fun onRetrievePostListSuccess(postList:List<Post>){
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(throws: Throwable){
        errorMessage.value = throws.message
    }
}