package com.example.app44.domain

import com.example.app44.core.BaseResult
import com.example.app44.data.dto.response.ListInvoiceResponse
import com.example.app44.data.api.DocumentApi
import javax.inject.Inject

class PrintInvoiceUseCase @Inject constructor(
    private val documentApi: DocumentApi,
) {
    suspend fun execute(
        page: Int = 1,
        pageSize: Int = 10,
        fromDate: String? = null,
        toDate: String? = null
    ): BaseResult<ListInvoiceResponse> {
        return try {
            val response = documentApi.getInvoices(
                page = page,
                pageSize = pageSize,
                fromDate = fromDate,
                toDate = toDate
            )
            BaseResult.Success(response)
        } catch (e: Exception) {
            BaseResult.Error(e.message.orEmpty())
        }
    }
}