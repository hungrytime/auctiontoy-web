package com.auctiontoyweb.DTO

import com.auctiontoyweb.ResultCode

data class ResponseDTO<T>(
    val success: Boolean = true,
    val code: String = "",
    val message: String? = null,
    val content: T? = null
) {
    companion object {
        fun <T> success(content: T? = null): ResponseDTO<T> {
            return ResponseDTO(success = true, "SUCCESS", content = content)
        }

        fun <T> fail(code: ResultCode, message: String? = null, content: T? = null): ResponseDTO<T> {
            return ResponseDTO(success = false, code = code.name, message = message, content = content)
        }
    }
}
