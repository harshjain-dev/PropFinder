package com.harshjaindev.propfinder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.harshjaindev.propfinder.ui.components.GroceryListItem
import com.harshjaindev.propfinder.ui.theme.PropFinderTheme

@Composable
fun GroceryScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val items by viewModel.filteredItems.collectAsStateWithLifecycle()
    val selectedId by viewModel.selectedCategoryId.collectAsStateWithLifecycle()
    val selectedFilterId by viewModel.selectedFilterCategoryId.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
    var editingItem by remember { mutableStateOf<GroceryItem?>(null) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .imePadding()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(key = "brand_card") {
                BrandCard()
            }

            item(key = "add_item_card") {
                AddItemCard(
                    modifier = Modifier.padding(horizontal = 12.dp),
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
            }

            item(key = "filter_row") {
                GroceryFilterRow(categories = categories, selectedFilterId, {
                    viewModel.selectFilterCategory(it)
                })
            }

            if (items.isEmpty()) {
                item(key = "empty_state") {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "",
                            modifier = Modifier.height(50.dp)
                        )
                        Text(
                            text = "Your Grocery List is empty",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Add items to get started",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            } else {
                items(items, key = { it.id }) { item ->
                    GroceryListItem(
                        item = item,
                        onCheckedChange = { changedItem, state ->
                            viewModel.onTogglePurchased(changedItem.id, state)
                        },
                        onDelete = {
                            viewModel.onDeleteItem(it.id)
                        },
                        onEdit = {
                            editingItem = it
                        }
                    )
                }
            }

        }

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

@Preview
@Composable
private fun GroceryScreenPreview() {
    PropFinderTheme {
//        GroceryScreen(viewModel = MainViewModel(GrRE))
    }
}
