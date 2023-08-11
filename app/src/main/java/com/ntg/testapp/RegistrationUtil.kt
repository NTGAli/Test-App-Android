package com.ntg.testapp

object RegistrationUtil {

    private val existingUsers = listOf("John", "Robert")

    /**
     * it returns false if
     * ...username or password is empty
     * ...username already exist
     * ...password and confirmedPassword are not same
     * ...use less than 2 digits
     * ...use less than one capital character
     * ...length of password less than 8 characters
     * */

    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmedPassword: String
    ): Boolean{
        if (username.isEmpty() || password.isEmpty()){
            return false
        }else if (username in existingUsers){
            return false
        }else if (password != confirmedPassword){
            return false
        }else if (password.count { it.isDigit() } < 2){
            return false
        }else if (password.count { it.isUpperCase() } < 2){
            return false
        }else if (password.length < 8){
            return false
        }
        return true
    }
}