package com.harshjaindev.domain.usecases

import com.harshjaindev.domain.repository.GroceryRepository
import javax.inject.Inject

class DeleteGroceryItemsUseCase @Inject constructor(
    private val groceryRepository: GroceryRepository
) {
    suspend operator fun invoke(id: String) {
        groceryRepository.delete(id)
    }
}