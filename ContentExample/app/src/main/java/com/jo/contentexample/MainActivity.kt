package com.jo.contentexample

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cursor = contentResolver.query(
            Uri.parse("content://com.jo.app.contentprovider/User"),
            null,
            null,
            null,
            null
        )

        val userList: MutableList<User> = mutableListOf()
        while (cursor != null && cursor.moveToNext()) {
            userList.add(
                User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                )
            )
        }

        findViewById<RecyclerView>(R.id.rv).apply {
            adapter = UserAdapter().apply {
                items = userList
            }
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        findViewById<Button>(R.id.btn_submit).setOnClickListener {
            val name = findViewById<EditText>(R.id.et_name).text.toString()
            val age = findViewById<EditText>(R.id.et_age).text.toString()
            val phone = findViewById<EditText>(R.id.et_phone).text.toString()

            val newValues = ContentValues().apply {
                put("NAME", name)
                put("AGE", age)
                put("PHONE", phone)
            }

            contentResolver.insert(
                Uri.parse("content://com.jo.app.contentprovider/User"),
                newValues
            )
        }
    }
}