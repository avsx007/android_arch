package com.android.kotlinmvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.kotlinmvvm.data.database.dao.MessageDao
import com.android.kotlinmvvm.data.database.entity.Message
import java.util.concurrent.Executors


@Database(entities = [Message::class], version = 1, exportSchema = false)
abstract class MessageDatabase : RoomDatabase() {

    internal abstract fun messageDao(): MessageDao

    companion object {

        // marking the instance as volatile to ensure atomic access to the variable
        @Volatile
        private var INSTANCE: MessageDatabase? = null
        private val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): MessageDatabase? {
            if (INSTANCE == null) {
                synchronized(MessageDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MessageDatabase::class.java, "message_database"
                        )
                           // .addCallback(sRoomDatabaseCallback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        /**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         *
         * If you want to populate the database only when the database is created for the 1st time,
         * override RoomDatabase.Callback()#onCreate
         */
        /*private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(@NonNull db: SupportSQLiteDatabase) {
                super.onOpen(db)

                // If you want to keep data through app restarts,
                // comment out the following block
                databaseWriteExecutor.execute({
                    // Populate the database in the background.
                    // If you want to start with more words, just add them.
                    val dao = INSTANCE!!.wordDao()
                    dao.deleteAll()

                    var word = Message(1)
                    dao.insert(word)
                    word = Word("World")
                    dao.insert(word)
                })
            }
        }*/
    }
}
