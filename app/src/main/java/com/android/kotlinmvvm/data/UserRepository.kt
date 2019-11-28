package com.android.kotlinmvvm.data

import com.android.kotlinmvvm.data.webservice.RestService
import com.android.kotlinmvvm.model.User
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() {

    @Inject
    lateinit var restService: RestService

    fun getUser(userId: String) : Single<Response<User>> {
        return restService?.getUser(userId = userId)
    }

    //all rest call, database call related to user will be done in this class
}