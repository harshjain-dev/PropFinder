package com.harshjaindev.data.repository

import com.harshjaindev.data.local.dao.CategoryDao
import com.harshjaindev.data.local.utils.CategorySeedLoader
import com.harshjaindev.domain.models.Category
import com.harshjaindev.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val dao: CategoryDao,
    private val seedLoader: CategorySeedLoader
) : CategoryRepository {
    override fun observeCategories(): Flow<List<Category>> =
        dao.observeAll().map { list ->
            list.map { e ->
                Category(
                    id = e.id,
                    name = e.name,
                    iconKey = e.iconKey,
                    bgColorHex = e.bgColorHex,
                    accentHex = e.accentHex,
                    description = e.description,
                    isSelected = e.isSelected
                )
            }
        }

    override suspend fun seedIfEmpty() {
        if (dao.count() == 0) {
            dao.insertAll(seedLoader.loadFromAssets())
        }
    }
}