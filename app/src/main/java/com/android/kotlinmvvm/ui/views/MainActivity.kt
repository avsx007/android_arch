package com.android.kotlinmvvm.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.kotlinmvvm.R
import com.android.kotlinmvvm.base.BaseActivity
import com.android.kotlinmvvm.ui.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel>() {
    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelType(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(UserProfileFragment())

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.rlHolder,fragment)
        fragmentTransaction.commit()
    }

}
