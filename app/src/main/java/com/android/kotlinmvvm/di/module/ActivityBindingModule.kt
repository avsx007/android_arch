package com.android.kotlinmvvm.di.module

import com.android.kotlinmvvm.di.module.fragment.MainFragmentBindingModule
import com.android.kotlinmvvm.ui.views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
    abstract fun bindMainActivity(): MainActivity
}