package com.auctiontoyweb.controller.form

import com.auctiontoyweb.PageParam

data class SearchCreatedItemListForm(
    val startDate: String?,
    val endDate: String?,
    override var page: Int = 0,
    override var size: Int = 10,
    override var totalPage: Int = 1
) : PageParam(page, size, totalPage)
