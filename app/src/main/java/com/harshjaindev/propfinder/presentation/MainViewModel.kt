package com.harshjaindev.propfinder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshjaindev.domain.models.GroceryItem
import com.harshjaindev.domain.usecases.AddGroceryItemUseCase
import com.harshjaindev.domain.usecases.DeleteGroceryItemsUseCase
import com.harshjaindev.domain.usecases.ObserveCategoriesUseCase
import com.harshjaindev.domain.usecases.ObserveGroceryItemsUseCase
import com.harshjaindev.domain.usecases.SeedCategoriesIfEmptyUseCase
import com.harshjaindev.domain.usecases.TogglePurchasedUseCase
import com.harshjaindev.domain.usecases.UpdateGroceryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addGroceryItemUseCase: AddGroceryItemUseCase,
    private val deleteGroceryItemsUseCase: DeleteGroceryItemsUseCase,
    private val observeCategoriesUseCase: ObserveCategoriesUseCase,
    private val observeGroceryItemsUseCase: ObserveGroceryItemsUseCase,
    private val togglePurchasedUseCase: TogglePurchasedUseCase,
    private val updateGroceryUseCase: UpdateGroceryUseCase,
    private val seedCategoriesIfEmptyUseCase: SeedCategoriesIfEmptyUseCase
) : ViewModel() {

    private val _selectedCategoryId = MutableStateFlow<String?>("milk")
    val selectedCategoryId = _selectedCategoryId.asStateFlow()

    private val _selectedFilterCategoryId = MutableStateFlow<String?>(null)
    val selectedFilterCategoryId = _selectedFilterCategoryId.asStateFlow()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    val categories = observeCategoriesUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())

    val filteredItems: StateFlow<List<GroceryItem>> =
        combine(
            observeGroceryItemsUseCase.invoke(),
            _selectedFilterCategoryId
        ) { items, selectedId ->
            if (selectedId == null) items
            else items.filter { it.categoryId == selectedId }
        }.stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            seedCategoriesIfEmptyUseCase.invoke()
        }
    }

    fun addItem(name: String, categoryId: String?) = viewModelScope.launch {
        onQueryChanged("")
        addGroceryItemUseCase.invoke(name, categoryId)
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

    fun onTogglePurchased(itemId: String, purchased: Boolean) = viewModelScope.launch {
        togglePurchasedUseCase(itemId, purchased)
    }

    fun onDeleteItem(itemId: String) = viewModelScope.launch {
        deleteGroceryItemsUseCase(itemId)
    }

    fun onEditItem(updated: GroceryItem) = viewModelScope.launch {
        updateGroceryUseCase(updated)
    }

    fun selectCategory(id: String?) {
        _selectedCategoryId.value = id
    }

    fun selectFilterCategory(id: String?) {
        _selectedFilterCategoryId.value = id
    }
}