package com.harshjaindev.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.harshjaindev.data.local.dao.CategoryDao
import com.harshjaindev.data.local.dao.GroceryDao
import com.harshjaindev.data.local.database.GroceryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseDI {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): GroceryDatabase =
        Room.databaseBuilder(context, GroceryDatabase::class.java, "grocery.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideGroceryDao(db: GroceryDatabase): GroceryDao = db.groceryDao()
    @Provides
    fun provideCategoryDao(db: GroceryDatabase): CategoryDao = db.categoryDao()

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}