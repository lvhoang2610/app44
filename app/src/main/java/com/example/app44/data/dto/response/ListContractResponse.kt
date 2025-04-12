package com.example.app44.data.dto.response

import com.squareup.moshi.Json


data class ListContractResponse(
    @Json(name = "items")
    val items: List<ContractResponse>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "current_page") val currentPage: Int,
    @Json(name = "pages")
    val pages: Int,
)

data class ContractResponse(
    val id: Int,
    @Json(name = "contract_number") val invoiceNumber: String,
    @Json(name = "issue_date") val issueDate: String,
    @Json(name = "file_path") val filePath: String
)


