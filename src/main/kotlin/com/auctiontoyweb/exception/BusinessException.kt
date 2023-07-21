package com.auctiontoyweb.exception

class BusinessException(
    override val message: String?,
): RuntimeException(message)