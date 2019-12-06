package com.android.kotlinmvvm.di.module

import android.app.Application
import android.content.Context
import com.android.kotlinmvvm.data.webservice.RestService
import com.android.kotlinmvvm.utils.Session
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule {

    @Module
    companion object {
        const val BASE_URL = "http://0.0.0.0/DataService.svc/";

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofit(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client =
                OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(40, TimeUnit.SECONDS)
                    .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofitService(retrofit: Retrofit): RestService {
            return retrofit.create<RestService>(RestService::class.java!!)
        }

         /*@JvmStatic
         @Provides
         @Singleton
         internal fun provideUserRepository(userRepository: UserRepository): UserRepository {
             return UserRepository()
         }*/

        @JvmStatic
        @Singleton
        @Provides
        fun provideSession(context: Context): Session {
            return Session(context)
        }
    }
}