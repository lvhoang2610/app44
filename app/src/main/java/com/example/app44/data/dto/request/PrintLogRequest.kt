package com.example.app44.data.dto.request

import com.squareup.moshi.Json

data class PrintLogRequest (
    @Json(name = "object_id")
    val objectId: String,
    @Json(name = "type")
    val type: String
)