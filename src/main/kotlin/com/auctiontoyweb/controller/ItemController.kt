package com.auctiontoyweb.controller

import com.auctiontoyweb.PageContent
import com.auctiontoyweb.RegisterItemVO
import com.auctiontoyweb.client.ItemService
import com.auctiontoyweb.client.UserInfoValue
import com.auctiontoyweb.controller.form.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Controller
class ItemController(
    val itemService: ItemService
) {

    @GetMapping("/item/register")
    fun register(model: Model): String{
        if (UserInfoValue.token.isNullOrBlank() || UserInfoValue.userId.isNullOrBlank()) return "redirect:/login"
        model.addAttribute("name", UserInfoValue.userName)
        model.addAttribute("id", UserInfoValue.userId)
        return "item/register"
    }

    @PostMapping("/item/register")
    fun register(@ModelAttribute registerItemForm: RegisterItemForm, model: Model): String {
        if(UserInfoValue.token.isNullOrBlank()){
            return "redirect:/login"
        }
        println(registerItemForm)

        var start: LocalDateTime? = null
        var end: LocalDateTime? = null
        try{
            start = LocalDateTime.parse(registerItemForm.startAuction, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
            end = LocalDateTime.parse(registerItemForm.endAuction, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
        } catch (e: Exception) {
            UserInfoValue.token = ""
            UserInfoValue.userId = ""
            return "redirect:/login"
        }
        if (start == null || end == null) {
            return "item/register"
        }
        if (start.isBefore(LocalDateTime.now())) {
            throw IllegalArgumentException( "경매 시작 날짜는 현재 시점보다 이후 이어야 합니다.")
        }

        if (start.isAfter(end).not()) {
            throw IllegalArgumentException( "경매 시작 날짜는 경매 종료 날짜보다 이전이어야 합니다.")
        }
        itemService.register(RegisterItemVO.from(registerItemForm, UserInfoValue.userId, start, end))


        return "redirect:/main"
    }

    @GetMapping("/item/list")
    fun getList(searchForm: SearchItemListForm, model: Model): String {
        if(UserInfoValue.token.isNullOrBlank()){
            return "redirect:/login"
        }
        val items = itemService.getItemList(searchForm.page, searchForm.size, searchForm.totalPage)
        model.addAttribute("name", UserInfoValue.userName)
        model.addAttribute("page", items)
        return "/item/list"
    }

    @GetMapping("/item/list/participant")
    fun getParticipantList(searchForm: SearchItemListForm, model: Model): String {
        if(UserInfoValue.token.isNullOrBlank()){
            return "redirect:/login"
        }
        val items = itemService.getParticipantItemList(searchForm.page, searchForm.size, searchForm.totalPage)
        model.addAttribute("name", UserInfoValue.userName)
        model.addAttribute("page", items)
        return "/item/listparticipant"
    }

    @GetMapping("/item/list/createdAt")
    fun getListWithCreatedAt(
        searchCreatedItemListForm: SearchCreatedItemListForm, model: Model
    ):String {
        if(UserInfoValue.token.isNullOrBlank()){
            return "redirect:/login"
        }
        if(searchCreatedItemListForm.startDate == null || searchCreatedItemListForm.endDate == null) {
            model.addAttribute("name", UserInfoValue.userName)
            model.addAttribute("page", PageContent.toPage(0,0, 0, null))
            return "/item/listcreated"
        }
        var start: LocalDateTime? = null
        var end: LocalDateTime? = null
        try{
            start = LocalDateTime.parse(searchCreatedItemListForm.startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
            end = LocalDateTime.parse(searchCreatedItemListForm.endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
        } catch (e: Exception) {
            UserInfoValue.token = ""
            UserInfoValue.userId = ""
            return "redirect:/login"
        }
        if (start == null || end == null) {
            model.addAttribute("name", UserInfoValue.userName)
            model.addAttribute("page", PageContent.toPage(0,0, 0, null))
            return "item/listcreated"
        }
        val items = itemService.getCreatedItemList(
            start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            searchCreatedItemListForm.page,
            searchCreatedItemListForm.size,
            searchCreatedItemListForm.totalPage
        )
        model.addAttribute("name", UserInfoValue.userName)
        model.addAttribute("page", items)
        return "/item/listcreated"

    }

    @GetMapping("/item/list/status")
    fun getListWithStatus(
        searchStatusItemListForm: SearchStatusItemListForm, model: Model
    ):String {
        if(UserInfoValue.token.isNullOrBlank()){
            return "redirect:/login"
        }
        if(searchStatusItemListForm.itemStatus == null){
            model.addAttribute("name", UserInfoValue.userName)
            model.addAttribute("page", PageContent.toPage(0,0, 0, null))
            return "/item/liststatus"
        }
        val items = itemService.getStatusItemList(
            searchStatusItemListForm.itemStatus!!,
            searchStatusItemListForm.page,
            searchStatusItemListForm.size,
            searchStatusItemListForm.totalPage
        )
        model.addAttribute("name", UserInfoValue.userName)
        model.addAttribute("page", items)
        return "/item/liststatus"
    }

    @GetMapping("/item/list/detail/{itemId}")
    fun getList(@PathVariable itemId: Long, model: Model): String {
        if(UserInfoValue.token.isNullOrBlank()){
            return "redirect:/login"
        }
        val item = itemService.getItemDetail(itemId)
        model.addAttribute("name", UserInfoValue.userName)
        model.addAttribute("items", item)

        return "/item/listdetail"
    }

    @PostMapping("/item/trybid")
    fun tryBid(
        @ModelAttribute bidForm: BidForm,
        model: Model
    ): String {
        itemService.tryBid(bidForm.price, bidForm.itemId)
//        val item = itemService.getItemDetail(bidForm.itemId)
//        model.addAttribute("name", UserInfoValue.userName)
//        model.addAttribute("items", item)

        return "redirect:/item/list/detail/${bidForm.itemId}"
    }

    @GetMapping("/item/mine")
    fun getListWithMine(
        searchForm: SearchItemListForm, model: Model
    ):String {
        if(UserInfoValue.token.isNullOrBlank()){
            return "redirect:/login"
        }
        val items = itemService.getItemListWithMine(
            searchForm.page,
            searchForm.size,
            searchForm.totalPage
        )
        model.addAttribute("name", UserInfoValue.userName)
        model.addAttribute("page", items)
        return "/item/listmine"
    }

    @GetMapping("/item/mine/detail/{itemId}")
    fun getListWithMine(
        @PathVariable itemId: Long, model: Model
    ):String {
        if(UserInfoValue.token.isNullOrBlank()){
            return "redirect:/login"
        }
        val item = itemService.getItemDetailWithMine(
            itemId
        )
        model.addAttribute("name", UserInfoValue.userName)
        model.addAttribute("item", item)
        return "/item/detailmine"
    }
}