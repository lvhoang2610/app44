package com.example.app44.enums

enum class PrintLogType(val type: String, val nameType: String) {
    INVOICE("invoice", "Hóa đơn"),
    DELIVERY_NOTE("delivery_note", "Vận đơn");

    companion object {
        fun fromType(type: String): String? {
            return entries.firstOrNull { it.type == type }?.nameType
        }
    }
}
