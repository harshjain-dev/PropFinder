package com.harshjaindev.domain.usecases

import com.harshjaindev.domain.models.GroceryItem
import com.harshjaindev.domain.repository.GroceryRepository
import javax.inject.Inject

class UpdateGroceryUseCase @Inject constructor(
    private val groceryRepository: GroceryRepository
) {
    suspend operator fun invoke(item: GroceryItem) {
        groceryRepository.update(item)
    }
}