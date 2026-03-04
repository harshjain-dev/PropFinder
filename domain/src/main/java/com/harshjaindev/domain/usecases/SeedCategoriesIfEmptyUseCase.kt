package com.harshjaindev.domain.usecases

import com.harshjaindev.domain.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeedCategoriesIfEmptyUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            categoryRepository.seedIfEmpty()
        }
    }
}