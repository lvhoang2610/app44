package com.example.app44.data.dto.response

import com.squareup.moshi.Json

data class ListBillResponse(
    @Json(name = "items")
    val items: List<BillResponse>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "current_page") val currentPage: Int,
    @Json(name = "pages")
    val pages: Int,
)

data class BillResponse(
    val id: Int,
    @Json(name = "bill_number") val billNumber: String,
    @Json(name = "issue_date") val issueDate: String,
    @Json(name = "file_path") val filePath: String
)