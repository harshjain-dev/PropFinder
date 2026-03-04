package com.harshjaindev.domain.models

data class Category(
    val id: String,
    val name: String,
    val iconKey: String,
    val bgColorHex: String,
    val accentHex: String,
    val description: String?,
    val isSelected: Boolean = false,
)