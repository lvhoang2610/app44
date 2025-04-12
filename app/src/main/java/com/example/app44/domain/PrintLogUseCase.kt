package com.example.app44.domain

import com.example.app44.core.BaseResult
import com.example.app44.data.api.DocumentApi
import com.example.app44.data.dto.request.PrintLogRequest
import com.example.app44.data.dto.response.ListInvoiceResponse
import com.example.app44.data.dto.response.ListPrintLogResponse
import com.example.app44.data.dto.response.PrintLogResponse
import javax.inject.Inject

class PrintLogUseCase @Inject constructor(
    private val documentApi: DocumentApi,
) {
    suspend fun execute(
        printLogRequest: PrintLogRequest
    ): BaseResult<PrintLogResponse> {
        return try {
            val response = documentApi.printLog(
                printLogRequest
            )
            BaseResult.Success(response)
        } catch (e: Exception) {
            BaseResult.Error(e.message.orEmpty())
        }
    }

    suspend fun getListPrintLog(
        page: Int,
        pageSize: Int,
        fromDate: String?,
        toDate: String?
    ): BaseResult<ListPrintLogResponse> {
        return try {
            val response = documentApi.getListPrintLog(
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