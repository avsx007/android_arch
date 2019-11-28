package com.android.kotlinmvvm.data.webservice

import com.android.kotlinmvvm.model.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestService {

    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String) : Single<Response<User>>

}