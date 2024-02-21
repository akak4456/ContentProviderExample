package com.jo.contentprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_submit).setOnClickListener {
            val name = findViewById<EditText>(R.id.et_name).text.toString()
            val age = findViewById<EditText>(R.id.et_age).text.toString()
            val phone = findViewById<EditText>(R.id.et_phone).text.toString()
            Thread {
                val db = UserDatabase.getInstance(this)
                db?.userDao()?.insert(User(name, age, phone))
                updateList()
            }.start()

        }

        findViewById<RecyclerView>(R.id.rv).apply {
            userAdapter = UserAdapter()
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        updateList()
    }

    private fun updateList() {
        Thread {
            val db = UserDatabase.getInstance(this)
            val userList = db?.userDao()?.getAll() ?: listOf()
            runOnUiThread {
                userAdapter.items = userList
            }
        }.start()
    }
}