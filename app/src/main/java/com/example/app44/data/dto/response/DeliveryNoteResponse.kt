package com.example.app44.data.dto.response

import com.squareup.moshi.Json

data class ListDeliveryNoteResponse(
    @Json(name = "items")
    val items: List<DeliveryNoteResponse>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "current_page") val currentPage: Int,
    @Json(name = "pages")
    val pages: Int,
)

data class DeliveryNoteResponse(
    val id: Int,
    @Json(name = "delivery_number") val deliveryNumber: String,
    @Json(name = "issue_date") val issueDate: String,
    @Json(name = "file_path") val filePath: String
)