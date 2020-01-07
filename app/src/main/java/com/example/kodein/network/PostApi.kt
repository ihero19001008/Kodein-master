package com.example.kodein.network

import io.reactivex.Observable
import com.example.kodein.model.Post
import com.example.kodein.model.PostRespone
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("api/v1/images/search?query=l")
    fun getPosts(): Observable<PostRespone> 
}