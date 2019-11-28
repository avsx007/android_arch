package com.android.kotlinmvvm.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.kotlinmvvm.data.MessageRepository
import com.android.kotlinmvvm.data.UserRepository
import com.android.kotlinmvvm.data.database.entity.Message
import com.android.kotlinmvvm.model.User
import com.android.kotlinmvvm.utils.Session
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(val userRepository: UserRepository
        , val messageRepository: MessageRepository) : ViewModel() {

    @Inject
    lateinit var session: Session
    private var disposable: CompositeDisposable? = CompositeDisposable()
    private var userData: MutableLiveData<User> = MutableLiveData()
    private var repoLoading: MutableLiveData<Boolean> = MutableLiveData()
    private var repoLoadError: MutableLiveData<String> = MutableLiveData()

    fun getUser(userId : String) : MutableLiveData<User> {
        repoLoading.value = true
        disposable?.add(userRepository.getUser(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<Response<User>>(){
                override fun onSuccess(response: Response<User>?) {
                    if(response!=null && response.code() == HTTP_OK) {
                        userData.value = response.body()
                    } else {
                        repoLoadError.value = response!!.message()
                    }
                    repoLoading.value = false
                }

                override fun onError(e: Throwable?) {
                    repoLoadError.value = e!!.message
                    repoLoading.value = false
                }
            }))
        userData.value = User(userId = "101", userName = "Abhishek");
        return userData
    }

    fun getMessage() = messageRepository.getMessages()

    fun setMessage(message: Message) = messageRepository.setMessage(message = message)

    fun getError(): LiveData<String> {
        return repoLoadError
    }

    fun getLoading(): LiveData<Boolean> {
        return repoLoading
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }
}