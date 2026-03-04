package com.harshjaindev.data.local.utils

data class SeedFile(
    val categories: List<SeedCategory>
)

data class SeedCategory(
    val categoryId: String,
    val displayName: String,
    val icon: String,
    val color: String,
    val accentColor: String,
    val description: String?,
    val isSelected: Boolean
)