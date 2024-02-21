package com.jo.contentprovider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var items: List<User> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class UserViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(item: User) {
            rootView.findViewById<TextView>(R.id.tv_user_info).text = "이름: ${item.name} 나이: ${item.age} 전화번호: ${item.phone}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }
}