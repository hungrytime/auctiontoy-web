package com.auctiontoyweb

data class ResponseDTO<T>(
    val code: String = "",
    val message: String? = null,
    val content: T? = null
) {
    companion object {
        fun <T> success(content: T? = null): ResponseDTO<T> {
            return ResponseDTO("SUCCESS", content = content)
        }

        fun <T> fail(code: ResultCode, message: String? = null, content: T? = null): ResponseDTO<T> {
            return ResponseDTO(code = code.name, message = message, content = content)
        }
    }
}
