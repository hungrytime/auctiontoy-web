package com.auctiontoyweb.DTO.item

import java.math.BigDecimal

data class BidItemDTO(
    val itemId: Long,
    val memberId: Long,
    val itemPrice: BigDecimal
)
