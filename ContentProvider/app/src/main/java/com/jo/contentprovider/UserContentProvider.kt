package com.jo.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.AbstractCursor
import android.database.Cursor
import android.net.Uri

class UserContentProvider : ContentProvider() {
    private lateinit var db: UserDatabase
    override fun onCreate(): Boolean {
        UserDatabase.getInstance(context!!)?.let {
            db = it
        }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        return db.userDao().getCursor()
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.com.jo.contentprovider.User"
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        values?.let {
            db.userDao().insert(
                User(
                    it.getAsString("NAME"),
                    it.getAsString("AGE"),
                    it.getAsString("PHONE")
                )
            )
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}