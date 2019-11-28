package com.android.kotlinmvvm.di.module.fragment

import com.android.kotlinmvvm.ui.views.UserProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun provideUserProfileFragment(): UserProfileFragment
}