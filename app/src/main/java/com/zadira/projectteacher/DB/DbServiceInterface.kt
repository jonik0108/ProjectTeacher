package com.zadira.projectteacher.DB

import com.zadira.test3.Model.User


interface DbServiceInterface {

    fun addUser(user: User)
    fun deleteUser(user: User)
    fun updateUser(user: User):Int
    fun getAllUser():List<User>

}