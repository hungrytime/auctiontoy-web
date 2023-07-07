package com.auctiontoyweb

import com.auctiontoyweb.controller.form.LogInForm

data class SignInMemberDTO(
    val id: String,
    val password: String
) {
    companion object {
        fun from(loginInfo : LogInForm) = SignInMemberDTO(
            id = loginInfo.userId!!,
            password = loginInfo.password!!
        )
    }
}
