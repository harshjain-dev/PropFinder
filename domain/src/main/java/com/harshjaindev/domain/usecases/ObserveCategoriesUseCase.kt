package com.harshjaindev.domain.usecases

import com.harshjaindev.domain.models.Category
import com.harshjaindev.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<List<Category>> = categoryRepository.observeCategories()
}