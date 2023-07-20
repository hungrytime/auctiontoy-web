package com.auctiontoyweb.exception

import com.auctiontoyweb.client.UserInfoValue
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.security.auth.login.LoginException

@ControllerAdvice
class ControllerAdvicer {

    @ExceptionHandler(value = [LogInErrorException::class])
    fun loginException(e: LogInErrorException) {
        println(e.message)
        println(e.path)
    }

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun illegalException(e: IllegalArgumentException, model: Model) : String {
        model.addAttribute("message", e.message)
        return "errorAlert"
    }

    @ExceptionHandler(value = [Exception::class])
    fun exception(e: Exception): String {
        println(e.message)
        return "redirect:/error"
    }
}