package com.harshjaindev.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.harshjaindev.data.local.dao.CategoryDao
import com.harshjaindev.data.local.dao.GroceryDao
import com.harshjaindev.data.local.entities.CategoryEntity
import com.harshjaindev.data.local.entities.GroceryItemEntity

@Database(
    entities = [GroceryItemEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GroceryDatabase : RoomDatabase() {
    abstract fun groceryDao(): GroceryDao
    abstract fun categoryDao(): CategoryDao
}