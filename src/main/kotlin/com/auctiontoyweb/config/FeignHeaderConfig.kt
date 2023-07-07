package com.auctiontoyweb.config

import com.auctiontoyweb.client.UserInfoValue
import feign.RequestInterceptor
import org.springframework.context.annotation.Bean


//@Configuration
class FeignHeaderConfig {

    @Bean
    fun clientHeaderInterceptor(): RequestInterceptor {
        val token = UserInfoValue.token
        println("Interceptor : $token")
        return RequestInterceptor {
            if (UserInfoValue.token.isNotBlank())
                it.header("Authorization", "Bearer ${UserInfoValue.token}")
        }
    }
}