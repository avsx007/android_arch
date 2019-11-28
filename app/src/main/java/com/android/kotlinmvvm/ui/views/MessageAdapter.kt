package com.android.kotlinmvvm.ui.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlinmvvm.R
import com.android.kotlinmvvm.data.database.entity.Message

class MessageAdapter (private val context: Context, private val messages: List<Message>?) : RecyclerView.Adapter<MessageAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ViewHolder {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.rv_message, viewGroup, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return messages?.size!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.messageTV.text = messages?.get(index)?.message
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var messageTV: TextView = itemView.findViewById(R.id.messageTextView) as TextView
    }

}