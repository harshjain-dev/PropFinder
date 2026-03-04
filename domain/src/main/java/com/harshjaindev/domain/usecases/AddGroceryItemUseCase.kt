package com.harshjaindev.domain.usecases

import com.harshjaindev.domain.repository.GroceryRepository
import javax.inject.Inject

class AddGroceryItemUseCase @Inject constructor(
    private val groceryRepository: GroceryRepository
) {
    suspend operator fun invoke(name: String, categoryId: String?) {
        groceryRepository.addItem(name, categoryId)
    }
}