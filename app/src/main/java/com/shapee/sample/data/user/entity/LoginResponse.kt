package com.shapee.sample.data.user.entity

class LoginResponse {
    var isSuccess:Boolean = true
    var message:String?  = ""
    var errorCode:Int = 0
    var user:User? = null
    var accessToken:String? = null

}