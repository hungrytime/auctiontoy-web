package com.auctiontoyweb.config

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception

//@Configuration
//class FeignErrorDecoder: ErrorDecoder {
//    override fun decode(methodKey: String?, response: Response?): Exception {
//        println(methodKey)
//        println()
//        println(response)
//        return Exception("CustomErrorDecoder")
//    }
//}