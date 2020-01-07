package com.example.kodein.post

import androidx.lifecycle.MutableLiveData
import com.example.kodein.base.BaseViewModel
import com.example.kodein.model.Post

class PostViewModel: BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post){
        postTitle.value = post.url
        postBody.value = post.site
    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody():MutableLiveData<String>{
        return postBody
    }
}