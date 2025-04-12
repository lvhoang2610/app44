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
import com.example.app44.data.dto.response.InvoiceResponse

@Composable
fun InvoiceItem(
    tile: String,
    releaseDate: String,
    filePath: String,
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
                text = tile, style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Ngày phát hành: $releaseDate",
                style = MaterialTheme.typography.bodyMedium
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
        tile = "INV-123456",
        releaseDate = "2023-10-01",
        filePath = "/path/to/invoice.pdf"
    )
}