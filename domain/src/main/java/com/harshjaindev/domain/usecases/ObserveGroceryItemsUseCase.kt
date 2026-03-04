package com.harshjaindev.domain.usecases

import com.harshjaindev.domain.models.GroceryItem
import com.harshjaindev.domain.repository.GroceryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveGroceryItemsUseCase @Inject constructor(
    private val groceryRepository: GroceryRepository
) {
    operator fun invoke(): Flow<List<GroceryItem>> = groceryRepository.observeItems()
}