package com.auctiontoyweb.controller

import com.auctiontoyweb.SignInMemberDTO
import com.auctiontoyweb.client.MemberService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController(
    val memberService: MemberService
) {

    @GetMapping("/hello")
    fun hello(): String {
        println("say hello")

        println(memberService.hello(1))
        val signInfo = SignInMemberDTO("user", "pass")
        val token = memberService.signIn(signInfo)
        return "hello"
    }
}