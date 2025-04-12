package com.example.app44.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app44.data.dto.response.InvoiceDto

@Composable
fun InvoiceItem(
    invoiceItem: InvoiceDto,
    onClickPreview: () -> Unit = {},
    onClickDownLoad: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = invoiceItem.invoiceNumber, style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Ngày phát hành: ${invoiceItem.issueDate}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Đường dẫn: ${invoiceItem.filePath}",
                style = MaterialTheme.typography.labelMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { onClickPreview() }) {
                    Text("Xem PDF")
                }

                OutlinedButton(onClick = { onClickDownLoad() }) {
                    Text("In PDF")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewInvoiceItem() {
    InvoiceItem(
        invoiceItem = InvoiceDto(
            id = 1,
            invoiceNumber = "INV-123456",
            issueDate = "2023-10-01",
            filePath = "/path/to/invoice.pdf"
        )
    )
}