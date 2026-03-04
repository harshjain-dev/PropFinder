package com.harshjaindev.propfinder.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harshjaindev.domain.models.Category
import com.harshjaindev.domain.models.GroceryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditItemDialog(
    item: GroceryItem,
    categories: List<Category>,
    onDismiss: () -> Unit,
    onSave: (GroceryItem) -> Unit
) {
    var name by remember { mutableStateOf(item.name) }
    var selectedCategoryId by remember { mutableStateOf(item.categoryId) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit item") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Item name") },
                    singleLine = true
                )

                Spacer(Modifier.height(12.dp))

                CategoryDropdown(
                    categories = categories,
                    selectedCategoryId = selectedCategoryId.orEmpty(),
                    onSelected = { selectedCategoryId = it }
                )
            }
        },
        confirmButton = {
            TextButton(
                enabled = name.trim().isNotEmpty() && selectedCategoryId?.isNotBlank() == true,
                onClick = {
                    // ✅ category always from known list
                    onSave(
                        item.copy(
                            name = name.trim(),
                            categoryId = selectedCategoryId
                        )
                    )
                }
            ) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}