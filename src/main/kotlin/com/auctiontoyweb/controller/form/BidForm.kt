package com.auctiontoyweb.controller.form

import java.math.BigDecimal

data class BidForm(
    val price: BigDecimal,
    val itemId: Long
)
