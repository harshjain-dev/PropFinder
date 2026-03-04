package com.harshjaindev.data.mappers

import com.harshjaindev.data.local.entities.GroceryItemEntity
import com.harshjaindev.domain.models.GroceryItem

fun GroceryItemEntity.toUi() = GroceryItem(
    id, name, categoryId, quantity, isPurchased, updatedAt
)

fun GroceryItem.toEntity() = GroceryItemEntity(
    id, name, categoryId, quantity, isPurchased, updatedAt
)