package com.auctiontoyweb.controller.form

import java.math.BigDecimal

data class RegisterItemForm(
    val itemName: String,
    val basePrice: BigDecimal,
    val desiredPrice: BigDecimal,
    val minimumPrice: BigDecimal,
    val startAuction: String,
    val endAuction: String
)
