package com.android.kotlinmvvm.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.android.kotlinmvvm.di.util.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM: ViewModel> : DaggerAppCompatActivity() {
    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun getViewModelType(): Class<VM>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: VM

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelType())
        setContentView(layoutRes())
    }

}