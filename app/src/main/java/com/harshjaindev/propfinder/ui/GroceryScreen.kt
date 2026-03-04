package com.harshjaindev.propfinder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harshjaindev.domain.models.GroceryItem
import com.harshjaindev.propfinder.presentation.MainViewModel
import com.harshjaindev.propfinder.ui.components.AddItemCard
import com.harshjaindev.propfinder.ui.components.BrandCard
import com.harshjaindev.propfinder.ui.components.EditItemDialog
import com.harshjaindev.propfinder.ui.components.GroceryFilterRow
import com.harshjaindev.propfinder.ui.components.GroceryListCard
import com.harshjaindev.propfinder.ui.theme.PropFinderTheme

@Composable
fun GroceryScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val items by viewModel.filteredItems.collectAsStateWithLifecycle()
    val selectedId by viewModel.selectedCategoryId.collectAsStateWithLifecycle()
    val selectedFilterId by viewModel.selectedFilterCategoryId.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
    var editingItem by remember { mutableStateOf<GroceryItem?>(null) }

    Scaffold(modifier = modifier.fillMaxSize(), containerColor = MaterialTheme.colorScheme.primary) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            BrandCard()
            Spacer(Modifier.height(12.dp))
            AddItemCard(
                modifier = Modifier.padding(12.dp),
                categories = categories,
                query = query,
                selectedId = selectedId,
                onClick = {
                    viewModel.selectCategory(it)
                },
                onQueryChanged = {
                    viewModel.onQueryChanged(it)
                },
                onAddItem = {
                    viewModel.addItem(it.name, it.categoryId)
                }
            )
            Spacer(Modifier.height(12.dp))
            GroceryFilterRow(categories = categories, selectedFilterId, {
                viewModel.selectFilterCategory(it)
            })
            Spacer(Modifier.height(12.dp))
            GroceryListCard(
                groceryList = items,
                onCheckedChange = { itemId, state ->
                    viewModel.onTogglePurchased(itemId.id, state)
                }, onEdit = {
                    editingItem = it
                }, onDelete = {
                    viewModel.onDeleteItem(it.id)
                })

            // show edit dialog if editingItem != null
            editingItem?.let { item ->
                EditItemDialog(
                    item = item,
                    categories = categories,
                    onDismiss = { editingItem = null },
                    onSave = { updated ->
                        viewModel.onEditItem(updated)
                        editingItem = null
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun GroceryScreenPreview() {
    PropFinderTheme {
//        GroceryScreen(viewModel = MainViewModel(GrRE))
    }
}