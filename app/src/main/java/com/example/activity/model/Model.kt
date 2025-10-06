package com.example.activity.model

class Model
{

    private val username = "login"
    private val password = "password"

    fun checkLogin(login : String): Boolean
    {
        return  login == username
    }

    fun checkPassword(newPassword: String): Boolean
    {
        return password == newPassword
    }
}