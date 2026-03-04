package com.harshjaindev.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harshjaindev.data.local.entities.GroceryItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {

    @Query("SELECT * FROM grocery_item ORDER BY updatedAt DESC")
    fun observeAll(): Flow<List<GroceryItemEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: GroceryItemEntity): Long

    @Query("DELETE FROM grocery_item WHERE id = :id")
    suspend fun deleteById(id: String): Int

    @Query("UPDATE grocery_item SET isPurchased = :purchased, updatedAt = :updatedAt WHERE id = :id")
    suspend fun setPurchased(id: String, purchased: Boolean, updatedAt: Long): Int
}