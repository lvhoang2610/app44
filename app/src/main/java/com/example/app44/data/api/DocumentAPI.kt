package com.example.app44.data.api

import com.example.app44.data.dto.request.ForgotPasswordRequest
import com.example.app44.data.dto.response.ListInvoiceResponse
import com.example.app44.data.dto.request.LoginRequest
import com.example.app44.data.dto.request.PrintLogRequest
import com.example.app44.data.dto.response.ForgotPasswordResponse
import com.example.app44.data.dto.response.ListBillResponse
import com.example.app44.data.dto.response.ListContractResponse
import com.example.app44.data.dto.response.ListDeliveryNoteResponse
import com.example.app44.data.dto.response.ListPrintLogResponse
import com.example.app44.data.dto.response.OtherDocumentListResponse
import com.example.app44.data.dto.response.PrintLogResponse
import com.example.app44.data.dto.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DocumentApi {

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body payload: LoginRequest
    ): TokenResponse

    @GET("/api/v1/invoice/")
    suspend fun getInvoices(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("from_date") fromDate: String?,
        @Query("to_date") toDate: String?
    ): ListInvoiceResponse

    @GET("/api/v1/delivery-note/")
    suspend fun getDeliveryNotes(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("from_date") fromDate: String?,
        @Query("to_date") toDate: String?
    ): ListDeliveryNoteResponse

    @GET("/api/v1/bill/")
    suspend fun getBills(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("from_date") fromDate: String?,
        @Query("to_date") toDate: String?
    ): ListBillResponse

    @GET("/api/v1/contract/")
    suspend fun getContracts(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("from_date") fromDate: String?,
        @Query("to_date") toDate: String?
    ): ListContractResponse

    @GET("/api/v1/other-document/")
    suspend fun getOtherDocuments(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("from_date") fromDate: String?,
        @Query("to_date") toDate: String?
    ): OtherDocumentListResponse

    @POST("/api/v1/print-log/")
    suspend fun printLog(
        @Body printLogRequest: PrintLogRequest
    ): PrintLogResponse

    @GET("api/v1/print-log")
    suspend fun getListPrintLog(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("from_date") fromDate: String?,
        @Query("to_date") toDate: String?
    ): ListPrintLogResponse

    @POST("/api/v1/auth/reset-password")
    suspend fun resetPassword(
        @Body payload: ForgotPasswordRequest
    ): ForgotPasswordResponse
}
