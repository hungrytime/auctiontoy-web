package com.auctiontoyweb.client

import com.auctiontoyweb.*
//import com.auctiontoyweb.config.ClientErrorDecoder
import com.auctiontoyweb.config.FeignConfig
import com.auctiontoyweb.config.FeignHeaderConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(name="item", url="127.0.0.1:8080", configuration = [FeignConfig::class, FeignHeaderConfig::class])
interface ItemClient {

    @PostMapping(
        "/item/register",
        produces = ["application/json"]
    )
    fun registerItem(
        @RequestBody item: RegisterItemVO): ResponseDTO<String>

    @GetMapping(
        "/item/find/participant",
        produces = ["application/json"]
    )
    fun getList(
        @RequestParam memberId: Long,  @RequestParam page: Int, @RequestParam size: Int
    ): PageResponseDTO<List<ItemListDTO>>

    @GetMapping(
        "/item/find/participanting",
        produces = ["application/json"]
    )
    fun getParticipantList(
        @RequestParam memberId: Long,  @RequestParam page: Int, @RequestParam size: Int
    ): PageResponseDTO<List<ItemListDTO>>

    @GetMapping(
        "/item/find/participant/createdAt",
        produces = ["application/json"]
    )
    fun getCreatedList(
        @RequestParam memberId: Long,
        @RequestParam page: Int, @RequestParam size: Int,
        @RequestParam startDate:String, @RequestParam endDate: String
    ): PageResponseDTO<List<ItemListDTO>>

    @GetMapping(
        "/item/find/participant/status",
        produces = ["application/json"]
    )
    fun getStatusList(
        @RequestParam memberId: Long,
        @RequestParam status: String,
        @RequestParam page: Int,
        @RequestParam size: Int
    ): PageResponseDTO<List<ItemListDTO>>

    @GetMapping(
        "/item/find/seller",
        produces = ["application/json"]
    )
    fun getListWithMine(
        @RequestParam memberId: Long,
        @RequestParam page: Int,
        @RequestParam size: Int
    ): PageResponseDTO<List<SellerItemListDTO>>

    @GetMapping(
        "/item/find/participant/{itemId}",
        produces = ["application/json"]
    )
    fun getItemDetail(
        @RequestParam itemId: String): ResponseDTO<ItemInfoDTO>

    @GetMapping(
        "/item/find/seller/detail/{itemId}",
        produces = ["application/json"]
    )
    fun getItemDetailWithMine(
        @PathVariable itemId: Long
    ): ResponseDTO<SellerItemDetailDTO>

    @PostMapping(
        "/item/bid",
        produces = ["application/json"]
    )
    fun tryBid(
        @RequestBody item: BidItemDTO
    ): ResponseDTO<Unit>
}