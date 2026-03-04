package com.harshjaindev.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val iconKey: String,
    val bgColorHex: String,
    val accentHex: String,
    val description: String?,
    val isSelected: Boolean = false
)