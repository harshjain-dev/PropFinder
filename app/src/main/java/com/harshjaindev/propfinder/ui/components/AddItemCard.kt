package com.harshjaindev.propfinder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harshjaindev.domain.models.Category
import com.harshjaindev.domain.models.GroceryItem
import com.harshjaindev.propfinder.ui.theme.PropFinderTheme

@Composable
fun AddItemCard(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    selectedId: String?,
    query: String?,
    onClick: (id: String) -> Unit,
    onQueryChanged: (query: String) -> Unit,
    onAddItem: (item: GroceryItem) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
                text = "Add New Item",
                color = Color.Black
            )

            Spacer(Modifier.height(16.dp))

            SearchTextView(query = query, onQueryChanged = onQueryChanged)

            Spacer(Modifier.height(16.dp))

            CategoryCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                categories,
                selectedId,
                query,
                onClick,
                onAddItem
            )
        }
    }
}

@Preview
@Composable
private fun AddItemCardPreview() {
    PropFinderTheme {
//        AddItemCard(viewModel = MainViewModel(), categories = categories)
    }
}