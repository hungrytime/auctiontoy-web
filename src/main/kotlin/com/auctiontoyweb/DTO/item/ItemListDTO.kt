package com.auctiontoyweb.DTO.item

import java.math.BigDecimal

data class ItemListDTO(
    val itemId: Long,
    val memberId: Long,
    val itemName: String,
    val isBidThisItem: Boolean = false,
    val itemStatus: String,
    val priceIBid: BigDecimal = BigDecimal.ZERO,
    val bestPrice: BigDecimal = BigDecimal.ZERO,
    val auctionStartTime: String,
    val auctionEndTime: String
) {
//    companion object {
//        fun from(item: ItemListVO) = ItemListDTO(
//            itemId = item.itemId,
//            memberId = item.memberId,
//            itemName = item.itemName,
//            itemStatus = item.itemStatus,
//            isBidThisItem = item.isBidThisItem,
//            priceIBid = item.priceIBid,
//            bestPrice = item.bestPrice,
//            auctionStartTime= item.auctionStartTime,
//            auctionEndTime = item.auctionEndTime
//        )
//    }
}
