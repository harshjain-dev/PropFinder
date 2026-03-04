package com.harshjaindev.domain.repository

import com.harshjaindev.domain.models.GroceryItem
import kotlinx.coroutines.flow.Flow

interface GroceryRepository {
    fun observeItems(): Flow<List<GroceryItem>>
    suspend fun addItem(name: String, categoryId: String?)
    suspend fun togglePurchased(id: String, purchased: Boolean)
    suspend fun delete(id: String)
    suspend fun update(item: GroceryItem)
}