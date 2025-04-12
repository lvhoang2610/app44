package com.example.app44.data.dto.response

import com.squareup.moshi.Json

data class ListPrintLogResponse(
    @Json(name = "items")
    val items: List<PrintLogResponse>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "pages")
    val page: Int,
    @Json(name = "current_page")
    val currentPage: Int
)

data class PrintLogResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "user_id")
    val userId: Int,
    @Json(name = "object_id")
    val objectId: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "created_at")
    val createdAt: String
)