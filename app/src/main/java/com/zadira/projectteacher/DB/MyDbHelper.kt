package com.zadira.projectteacher.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.zadira.test3.Model.User

class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, Constant.DB_NAME, null, Constant.DB_VERSION), DbServiceInterface {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table " +
                    "${Constant.TABLE_NAME} (" +
                    "${Constant.ID}  integer not null primary key autoincrement unique," +
                    "${Constant.LESSON_NAME} text not null," +
                    "${Constant.GROUP}text not null," +
                    "${Constant.ROOM} text not null," +
                    "${Constant.TIME} text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // eski versiyani o'chirib tashlash
        p0?.execSQL("drop table if exists ${Constant.TABLE_NAME}")
        onCreate(p0)
    }

    override fun addUser(user: User) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(Constant.LESSON_NAME, user.lessonName1)
        contentValue.put(Constant.GROUP, user.group1)
        contentValue.put(Constant.ROOM, user.room1)
        contentValue.put(Constant.TIME, user.time1)
        Log.d("MyLog", contentValue.toString())
        database.insert(Constant.TABLE_NAME, null, contentValue)
        database.close()
    }

    override fun deleteUser(user: User) {
        val database = this.writableDatabase
        database.delete(Constant.TABLE_NAME, "${Constant.ID} =?", arrayOf(user.id.toString()))
        database.close()
    }

    override fun updateUser(user: User): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.ID, user.id)
        contentValues.put(Constant.LESSON_NAME, user.lessonName1)
        contentValues.put(Constant.GROUP, user.group1)
        contentValues.put(Constant.ROOM, user.room1)
        contentValues.put(Constant.TIME, user.time1)

        return database.update(
            Constant.TABLE_NAME,
            contentValues,
            "${Constant.ID} = ?",
            arrayOf(user.id.toString())
        )
    }

    override fun getAllUser(): List<User> {
        val list = ArrayList<User>()
        val query = "select * from ${Constant.TABLE_NAME}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val lessonname = cursor.getString(1)
                val group = cursor.getString(2)
                val room = cursor.getString(3)
                val time = cursor.getString(4)
                val user = User(id, lessonname, group, room, time)
                list.add(user)
            } while (cursor.moveToNext())
        }
        return list
    }
}