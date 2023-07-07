package com.auctiontoyweb.config

import com.auctiontoyweb.ResponseDTO
import com.auctiontoyweb.client.UserInfoValue
import com.auctiontoyweb.exception.LogInErrorException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import java.io.IOException
import java.security.Timestamp


class ClientErrorDecoder(
    val feignErrorDecoder: FeignErrorDecoder
): ErrorDecoder {

    override fun decode(methodKey: String?, response: Response?): Exception {

        var message: String? = null
        try {
            if (response != null)
                message = String(response.body().asInputStream().readAllBytes())
        } catch (e: IOException) {
            return java.lang.Exception(e.message)
        }

        val obj = jacksonObjectMapper().readValue(message, CommonResponse::class.java)

        if (obj.path != null) {
            UserInfoValue.token = ""
            UserInfoValue.userId = ""
            throw LogInErrorException(path = obj.path, cause = null, message = "로그인으로 redirect")
        }

        return when (response?.status()) {
            400 -> Exception(message ?: "Bad Request")
            404 -> Exception(message ?: "Not found")
            else -> feignErrorDecoder.decode(methodKey, response)
        }
    }
}

data class CommonResponse(
    val timestamp: String?,
    val status: Long?,
    val error: String?,
    val message: String?,
    val path: String?
)