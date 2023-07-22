package com.auctiontoyweb.DTO.member

import com.auctiontoyweb.controller.form.SignUpForm

class SignUpMemberDTO(
    val id: String,
    val password: String,
    val name: String
) {
    companion object {
        fun from(signUpInfo : SignUpForm) = SignUpMemberDTO(
            id = signUpInfo.userId!!,
            password = signUpInfo.password!!,
            name = signUpInfo.name!!
        )
    }
}