package com.harshjaindev.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_item")
data class GroceryItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val categoryId: String?,
    val quantity: Int = 1,
    val isPurchased: Boolean = false,
    val updatedAt: Long = System.currentTimeMillis()
)