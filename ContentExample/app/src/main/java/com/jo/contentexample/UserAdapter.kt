package com.jo.contentexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    var items: List<User> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class UserViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(item: User) {
            rootView.findViewById<EditText>(R.id.et_name).setText(item.name)
            rootView.findViewById<EditText>(R.id.et_age).setText(item.age)
            rootView.findViewById<EditText>(R.id.et_phone).setText(item.phone)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return UserViewHolder(inflater.inflate(R.layout.item_user_info, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }
}