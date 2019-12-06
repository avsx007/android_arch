package com.android.kotlinmvvm.di

import android.app.Application
import com.android.kotlinmvvm.base.BaseApplication
import com.android.kotlinmvvm.di.module.ActivityBindingModule
import com.android.kotlinmvvm.di.module.ApplicationModule
import com.android.kotlinmvvm.di.module.ContextModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [ContextModule::class,AndroidSupportInjectionModule::class, ApplicationModule::class, ActivityBindingModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

}