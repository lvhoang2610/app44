package com.example.app44.data.dto.response

import com.squareup.moshi.Json

data class OtherDocumentListResponse(
    @Json(name = "items")
    val items: List<OtherDocumentResponse>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "current_page") val currentPage: Int,
    @Json(name = "pages")
    val pages: Int,
)

data class OtherDocumentResponse(
    val id: Int,
    @Json(name = "document_number") val documentNumber: String,
    @Json(name = "issue_date") val issueDate: String,
    @Json(name = "file_path") val filePath: String
)
