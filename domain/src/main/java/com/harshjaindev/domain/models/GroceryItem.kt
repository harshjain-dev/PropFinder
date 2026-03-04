package com.harshjaindev.domain.models

data class GroceryItem(
    val id: String,
    val name: String,
    val categoryId: String?,
    val quantity: Int,
    val isPurchased: Boolean,
    val updatedAt: Long
)