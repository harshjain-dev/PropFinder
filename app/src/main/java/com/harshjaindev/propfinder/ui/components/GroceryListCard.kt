package com.harshjaindev.propfinder.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harshjaindev.domain.models.GroceryItem

@Composable
fun GroceryListCard(
    modifier: Modifier = Modifier,
    groceryList: List<GroceryItem> = emptyList(),
    onCheckedChange: (GroceryItem, Boolean) -> Unit,
    onDelete: (GroceryItem) -> Unit,
    onEdit: (GroceryItem) -> Unit
) {
    if (groceryList.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
            )
            Text(
                text = "Your Grocery List is empty",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Add items to get started",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxWidth()
        ) {
            items(groceryList, key = { item -> item.id }) { item ->
                GroceryListItem(item = item, onCheckedChange, onDelete, onEdit)
            }
        }
    }
}

@Composable
fun GroceryListItem(
    item: GroceryItem,
    onCheckedChange: (GroceryItem, Boolean) -> Unit,
    onDelete: (GroceryItem) -> Unit,
    onEdit: (GroceryItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = item.isPurchased,
                onCheckedChange = {
                    onCheckedChange(item, it)
                }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {

                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = if (item.isPurchased)
                        TextDecoration.LineThrough
                    else null
                )

                Spacer(Modifier.height(4.dp))

                AssistChip(
                    onClick = {},
                    shape = RoundedCornerShape(2.dp),
                    label = { item.categoryId?.uppercase()?.let { Text(it) } }
                )
            }

            IconButton(onClick = {
                onEdit(item)
            }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }

            IconButton(onClick = {
                onDelete(item)
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Preview
@Composable
private fun GroceryListCardPreview() {
//    PropFinderTheme {
//        GroceryListCard()
//    }
}