package com.example.app44.data.dto.response

import com.squareup.moshi.Json

data class ListInvoiceResponse(
    @Json(name = "items")
    val items: List<InvoiceDto>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "current_page") val currentPage: Int,
    @Json(name = "pages")
    val pages: Int,
)

data class InvoiceDto(
    val id: Int,
    @Json(name = "invoice_number") val invoiceNumber: String,
    @Json(name = "issue_date") val issueDate: String,
    @Json(name = "file_path") val filePath: String
)


