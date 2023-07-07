package com.auctiontoyweb.exception

class LogInErrorException(
    override val cause: Throwable?,
    override val message: String?,
    val path: String
): RuntimeException(message, cause)