package com.auctiontoyweb.exception

import com.auctiontoyweb.client.UserInfoValue
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.security.auth.login.LoginException

@RestControllerAdvice
class ControllerAdvicer {

    @ExceptionHandler(value = [LogInErrorException::class])
    fun loginException(e: LogInErrorException) {
        println(e.message)
        println(e.path)
    }

    @ExceptionHandler(value = [Exception::class])
    fun exception(e: Exception): String {
        println(e.message)
        return "redirect:/error"
    }
}