package com.auctiontoyweb

import java.math.BigDecimal

data class SellerItemDetailDTO(
    val itemId: Long,
    val itemName: String,
    val auctionStartTime: String,
    val auctionEndTime: String,
    val basePrice: BigDecimal,
    val desirePrice: BigDecimal,
    val realTimePrice: BigDecimal,
    val minimumPrice: BigDecimal,
    val itemStatus: String,
    val bidCount: Long,
    val highestBidMemberName: String?,
    val lastBidTime: String?
)
