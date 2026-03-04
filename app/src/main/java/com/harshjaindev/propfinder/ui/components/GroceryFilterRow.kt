package com.harshjaindev.propfinder.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harshjaindev.domain.models.Category

@Composable
fun GroceryFilterRow(
    categories: List<Category>,
    selectedCategoryId: String?,
    onCategorySelected: (String?) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // "All" filter
        item(key = "all_categories_filter") {
            FilterChip(
                selected = selectedCategoryId == null,
                onClick = { onCategorySelected(null) },
                label = { Text("All") }
            )
        }

        items(categories, key = { it.id }) { category ->
            FilterChip(
                selected = selectedCategoryId == category.id,
                onClick = {
                    if (selectedCategoryId == category.id)
                        onCategorySelected(null)
                    else
                        onCategorySelected(category.id)
                },
                label = { Text(category.name) }
            )
        }
    }
}
