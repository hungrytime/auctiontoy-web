package com.auctiontoyweb.client

import com.auctiontoyweb.*
import com.auctiontoyweb.DTO.item.*
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ItemService(
    val itemClient: ItemClient
) {

    fun register(item: RegisterItemVO) {
        itemClient.registerItem(item)
    }

    fun getItemList(page: Int, size: Int, totalPage: Int): PageContent<List<ItemListDTO>> {
        val ret = itemClient.getList(memberId = UserInfoValue.userMemberId, page, size)
        return PageContent.toPage(ret.totalPage,size, ret.page, ret.content ?: listOf())
    }

    fun getParticipantItemList(page: Int, size: Int, totalPage: Int): PageContent<List<ItemListDTO>> {
        val ret = itemClient.getParticipantList(memberId = UserInfoValue.userMemberId, page, size)
        return PageContent.toPage(ret.totalPage,size, ret.page, ret.content ?: listOf())
    }

    fun getCreatedItemList(
        startDate: String,
        endDate: String,
        page: Int,
        size: Int,
        totalPage: Int
    ): PageContent<List<ItemListDTO>> {

        val ret = itemClient.getCreatedList(memberId = UserInfoValue.userMemberId, page, size, startDate, endDate)
        return PageContent.toPage(ret.totalPage,size, ret.page, ret.content ?: listOf())
    }

    fun getStatusItemList(
        status: String,
        page: Int,
        size: Int,
        totalPage: Int
    ): PageContent<List<ItemListDTO>> {

        val ret = itemClient.getStatusList(memberId = UserInfoValue.userMemberId, status, page, size)
        return PageContent.toPage(ret.totalPage,size, ret.page, ret.content ?: listOf())
    }

    fun getItemDetail(itemId: Long): ItemInfoDTO? {
        val ret = itemClient.getItemDetail(itemId.toString())
        return ret.content
    }

    fun getItemListWithMine(
        page: Int,
        size: Int,
        totalPage: Int
    ): PageContent<List<SellerItemListDTO>> {

        val ret = itemClient.getListWithMine(memberId = UserInfoValue.userMemberId, page, size)
        return PageContent.toPage(ret.totalPage,size, ret.page, ret.content ?: listOf())
    }

    fun tryBid(
        price: BigDecimal,
        itemId: Long
    ) {
        itemClient.tryBid(BidItemDTO(itemId, UserInfoValue.userMemberId, price))
        return
    }

    fun getItemDetailWithMine(itemId: Long): SellerItemDetailDTO? {
        val ret = itemClient.getItemDetailWithMine(itemId)
        return ret.content
    }
}