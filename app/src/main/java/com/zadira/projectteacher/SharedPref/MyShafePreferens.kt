package com.zadira.projectteacher.SharedPref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zadira.test3.Model.User


object MyShafePreferens {
    private const val NAME = "ONE"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferens: SharedPreferences

    fun init(context: Context) {
        preferens = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var objectsString: ArrayList<User>
        get() = gsonStringToArray(preferens.getString("key0", "[]")!!)
        set(value) = preferens.edit() {
            it.putString("key0", arrayTogsonString(value))
        }

    private fun arrayTogsonString(value: ArrayList<User>): String? {
        return Gson().toJson(value)
    }

    private fun gsonStringToArray(string: String?):ArrayList<User> {
        val typetoken = object : TypeToken<ArrayList<User>>() {}.type
        return Gson().fromJson(string, typetoken)
    }


}