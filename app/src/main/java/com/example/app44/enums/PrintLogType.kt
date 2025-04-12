package com.example.app44.enums

enum class PrintLogType(val type: String, val nameType: String) {
    INVOICE("invoice", "Hóa đơn"),
    DELIVERY_NOTE("delivery_note", "Phiếu xuất kho"),
    CONTRACT("contract", "Hợp đồng Điện tử"),
    BILL("bill", "Vận đơn"),
    OTHER_DOCUMENT("other_document", "Chứng từ khác");


    companion object {
        fun fromType(type: String): String? {
            return entries.firstOrNull { it.type == type }?.nameType
        }
    }
}
