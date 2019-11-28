package com.android.kotlinmvvm.data

class MutableResponse<T> {
    var body: T? = null
    var error: String? = null
}