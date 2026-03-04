package com.harshjaindev.data.di

import com.harshjaindev.data.repository.CategoryRepositoryImpl
import com.harshjaindev.data.repository.GroceryRepositoryImpl
import com.harshjaindev.domain.repository.CategoryRepository
import com.harshjaindev.domain.repository.GroceryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGroceryRepository(
        impl: GroceryRepositoryImpl
    ): GroceryRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository
}