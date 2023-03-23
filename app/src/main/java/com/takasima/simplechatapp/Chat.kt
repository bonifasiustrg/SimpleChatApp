package com.takasima.simplechatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Chat : AppCompatActivity() {
    private lateinit var chatRV: RecyclerView
    private lateinit var messegeBox : EditText
    private lateinit var sentButton : Button

    private lateinit var messegeAdapter: MessegeAdapter
    private lateinit var messegeList: ArrayList<Messege>
    private lateinit var mDBRef: DatabaseReference

    var receiverRoom: String? = null
    var senderRoom: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")
        val senderUid = FirebaseAuth.getInstance().currentUser!!.uid

        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid
        mDBRef = FirebaseDatabase.getInstance().getReference()

        supportActionBar?.title = name

        chatRV = findViewById(R.id.rv_chat)
        messegeBox = findViewById(R.id.et_messege)
        sentButton = findViewById(R.id.sentButton)

        messegeList = ArrayList()
        messegeAdapter = MessegeAdapter(this, messegeList)


        // adding the messege to the database
        sentButton.setOnClickListener {
            val messege = messegeBox.text.toString()
            val messegeObject = Messege(messege, senderUid)

            mDBRef.child("chat").child(senderUid).child("messeges").push()
            
        }

    }
}