package com.harshjaindev.domain.repository

import com.harshjaindev.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun observeCategories(): Flow<List<Category>>
    suspend fun seedIfEmpty()
}