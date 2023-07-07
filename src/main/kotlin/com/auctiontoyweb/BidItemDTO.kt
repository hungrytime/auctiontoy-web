package com.auctiontoyweb

import java.math.BigDecimal

data class BidItemDTO(
    val itemId: Long,
    val memberId: Long,
    val itemPrice: BigDecimal
)
