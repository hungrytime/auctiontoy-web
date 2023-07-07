package com.auctiontoyweb.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import feign.Logger
import feign.codec.Decoder
import feign.codec.Encoder
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import org.springframework.context.annotation.Bean
import java.util.*


//@Configuration
class FeignConfig {
    @Bean
    fun feignLoggerLevel(): Logger.Level? {
        return Logger.Level.FULL
    }

//    @Bean
//    fun feignDecoder(): Decoder {
//        val gson: Gson = GsonBuilder()
////            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//            .create()
//        return GsonDecoder(gson)
//    }
//
//    @Bean
//    fun feignEncoder(): Encoder {
//        val gson: Gson = GsonBuilder()
////            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//            .create()
//        return GsonEncoder(gson)
//    }

//    private fun feignHttpMessageConverters(): ObjectFactory<HttpMessageConverters> {
//        return ObjectFactory<HttpMessageConverters> { HttpMessageConverters(GsonHttpMessageConverter()) }
//    }
}