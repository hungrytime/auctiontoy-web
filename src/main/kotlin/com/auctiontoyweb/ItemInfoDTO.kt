package com.auctiontoyweb

import java.math.BigDecimal
import java.time.LocalDateTime

data class ItemInfoDTO(
    val itemId: Long?,
    val itemName: String,
    val basePrice: BigDecimal,
    val desiredPrice: BigDecimal,
    val minimumPrice: BigDecimal,
    val auctionStartTime: LocalDateTime,
    val auctionEndTime: LocalDateTime,
    val itemStatus: String,
    val highestBidMemberName: String?,
    val realTimePrice: BigDecimal,
    val lastBidTime: LocalDateTime?
)
