package com.example.app44.core

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

object PDFDownloader {
    fun downloadPdf(
        context: Context,
        url: String,
        fileName: String
    ) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("Downloading PDF")
            .setDescription("Downloading $fileName")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        val downloadManager =
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }
}