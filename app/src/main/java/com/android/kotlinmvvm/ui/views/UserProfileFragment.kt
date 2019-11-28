package com.android.kotlinmvvm.ui.views

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.kotlinmvvm.R
import com.android.kotlinmvvm.base.BaseFragment
import com.android.kotlinmvvm.data.database.entity.Message
import com.android.kotlinmvvm.ui.viewmodel.UserProfileViewModel
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfileFragment : BaseFragment<UserProfileViewModel>() {
    lateinit var adapter:MessageAdapter
    private val DEFAULT_MSG_LENGTH_LIMIT = 100

    override fun layoutRes(): Int {
        return R.layout.fragment_user_profile
    }

    override fun getViewModelType(): Class<UserProfileViewModel> {
        return UserProfileViewModel::class.java
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getLoading().observe(this, Observer {
            pbProgress.visibility = if(it) View.VISIBLE else View.GONE
            Log.i(this.javaClass.simpleName,"loading : $it")
        })

        viewModel.getUser("101").observe(this, Observer {
            tvText.setText("userId : ${it.userId} userName : ${it.userName}")
        })

        viewModel.getMessage()?.observe(this, Observer {
            renderMessges(it)
        })

        // Enable Send button when there's text to send
        messageEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                sendButton.isEnabled = charSequence.toString().trim { it <= ' ' }.isNotEmpty()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        messageEditText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT))

        // Send button sends a message and clears the EditText
        sendButton.setOnClickListener {
            val message = Message(0,messageEditText.getText().toString())

            viewModel.setMessage(message)
            // Clear input box
            messageEditText.setText("")
        }
    }

    private fun renderMessges(messages: List<Message>?){
        adapter = MessageAdapter(context!!,messages)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.stackFromEnd = true
        messageRecyclerView.layoutManager = layoutManager
        messageRecyclerView.adapter = adapter
    }
}
