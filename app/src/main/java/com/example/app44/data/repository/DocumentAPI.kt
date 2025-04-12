package com.example.app44.data.repository

import com.example.app44.data.dto.BillOfLadingDto
import com.example.app44.data.dto.ContractDto
import com.example.app44.data.dto.DeliveryNoteDto
import com.example.app44.data.dto.response.ListInvoiceResponse
import com.example.app44.data.dto.request.LoginRequest
import com.example.app44.data.dto.OtherDocumentDto
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

    @GET("/delivery-notes")
    suspend fun getDeliveryNotes(): List<DeliveryNoteDto>

    @GET("/bills-of-lading")
    suspend fun getBillsOfLading(): List<BillOfLadingDto>

    @GET("/contracts")
    suspend fun getContracts(): List<ContractDto>

    @GET("/other-documents")
    suspend fun getOtherDocuments(): List<OtherDocumentDto>
}
