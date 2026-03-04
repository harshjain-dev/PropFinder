package com.harshjaindev.data.repository

import com.harshjaindev.data.local.dao.GroceryDao
import com.harshjaindev.data.local.entities.GroceryItemEntity
import com.harshjaindev.data.mappers.toEntity
import com.harshjaindev.data.mappers.toUi
import com.harshjaindev.domain.models.GroceryItem
import com.harshjaindev.domain.repository.GroceryRepository
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroceryRepositoryImpl @Inject constructor(
    private val dao: GroceryDao
) : GroceryRepository {
    override fun observeItems() = dao.observeAll().map {
        it.map { item ->
            item.toUi()
        }
    }

    override suspend fun addItem(name: String, categoryId: String?) {
        dao.upsert(
            GroceryItemEntity(
                id = UUID.randomUUID().toString(),
                name = name.trim(),
                categoryId = categoryId,
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun togglePurchased(id: String, purchased: Boolean) {
        dao.setPurchased(id, purchased, System.currentTimeMillis())
    }

    override suspend fun delete(id: String) {
        dao.deleteById(id)
    }

    override suspend fun update(item: GroceryItem) {
        dao.upsert(item.copy(updatedAt = System.currentTimeMillis()).toEntity())
    }
}