package com.takasima.simplechatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text
import java.lang.reflect.GenericArrayType

class MessegeAdapter(val context: Context, val messegeList: ArrayList<Messege>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_SENT = 1
    val ITEM_RECEIVE = 2


    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sentMessege = itemView.findViewById<TextView>(R.id.txt_sent_messege)
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receiveMessege = itemView.findViewById<TextView>(R.id.txt_receive_messege)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            //inflate receive
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive, parent, false)
            return ReceiveViewHolder(view)
        } else {
            //inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessege = messegeList[position]
        if (holder.javaClass == SentViewHolder::class.java) {
            //Step
            val viewHolder = holder as SentViewHolder
            holder.sentMessege.text = currentMessege.messege
        } else if (holder.javaClass == ReceiveViewHolder::class.java) {
            //step

            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessege.text = currentMessege.messege
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessege = messegeList[position]

        if (FirebaseAuth.getInstance().currentUser?.uid == currentMessege.senderID) {
            return ITEM_SENT
        } else {
            return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return messegeList.size
    }
}