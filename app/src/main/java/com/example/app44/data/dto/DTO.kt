package com.example.app44.data.dto

import com.squareup.moshi.Json


    data class DeliveryNoteDto(
        val id: Int,
        @Json(name = "delivery_number") val deliveryNumber: String,
        @Json(name = "issue_date") val issueDate: String,
        @Json(name = "file_path") val filePath: String
    )

    data class BillOfLadingDto(
        val id: Int,
        @Json(name = "bill_number") val billNumber: String,
        @Json(name = "issue_date") val issueDate: String,
        @Json(name = "file_path") val filePath: String
    )

    data class ContractDto(
        val id: Int,
        @Json(name = "contract_number") val contractNumber: String,
        @Json(name = "issue_date") val issueDate: String,
        @Json(name = "file_path") val filePath: String
    )

    data class OtherDocumentDto(
        val id: Int,
        @Json(name = "document_number") val documentNumber: String,
        @Json(name = "issue_date") val issueDate: String,
        @Json(name = "file_path") val filePath: String
    )