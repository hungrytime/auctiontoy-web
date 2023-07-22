package com.auctiontoyweb

data class PageContent<T>(
    val totalPage: Int,
    val page: Int,
    val contents: T
) {
    companion object {
        fun <T> toPage(totalPage: Int, pageSize: Int, page: Int, contents: T): PageContent<T> {
            if (pageSize == 0) return PageContent(0,0,contents)
            return PageContent(totalPage, page, contents)
        }
    }
}
