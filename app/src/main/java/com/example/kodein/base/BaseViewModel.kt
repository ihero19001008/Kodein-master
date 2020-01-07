package com.example.kodein.base

import androidx.lifecycle.ViewModel
import com.example.kodein.DaggerViewModelInjector
import com.example.kodein.NetworkModule
import com.example.kodein.ViewModelInjector
import com.example.kodein.post.PostListViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}