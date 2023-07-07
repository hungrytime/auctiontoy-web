package com.auctiontoyweb

import com.auctiontoyweb.controller.form.RegisterItemForm
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class RegisterItemVO(
    val memberUserId: String,
    val itemName: String,
    val basePrice: BigDecimal,
    val desiredPrice: BigDecimal,
    val minimumPrice: BigDecimal,
    val auctionStartTime: String,
    val auctionEndTime: String
) {
    companion object {
        fun from(item: RegisterItemForm, userId: String, start: LocalDateTime, end: LocalDateTime) = RegisterItemVO(
            memberUserId = userId,
            itemName = item.itemName,
            basePrice = item.basePrice,
            desiredPrice = item.desiredPrice,
            minimumPrice = item.minimumPrice,
            auctionStartTime = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            auctionEndTime = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        )
    }
}
