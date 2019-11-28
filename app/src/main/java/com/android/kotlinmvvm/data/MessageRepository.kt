package com.android.kotlinmvvm.data

import android.content.Context
import com.android.kotlinmvvm.data.database.MessageDatabase
import com.android.kotlinmvvm.data.database.dao.MessageDao
import com.android.kotlinmvvm.data.database.entity.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class MessageRepository @Inject constructor(context: Context) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var messageDao: MessageDao?

    init {
        val db = MessageDatabase.getDatabase(context)
        messageDao = db?.messageDao()
    }

    private suspend fun setMessageBG(message: Message){
        withContext(Dispatchers.IO){
            messageDao?.setMessage(message)
        }
    }

    fun getMessages() = messageDao?.getMessages()

    fun setMessage(message: Message) {
        launch  { setMessageBG(message) }
    }


}