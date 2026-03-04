package com.harshjaindev.propfinder.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.CrueltyFree
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.harshjaindev.domain.models.Category
import com.harshjaindev.domain.models.GroceryItem
import com.harshjaindev.propfinder.ui.theme.PropFinderTheme
import kotlin.uuid.ExperimentalUuidApi

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    selectedId: String?,
    query: String?,
    onClick: (id: String) -> Unit,
    onAddItem: (item: GroceryItem) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(text = "Category", color = Color.Black)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            categories.forEach {
                val isSelected = it.id == selectedId
                CategoryCardItem(modifier = Modifier.weight(1f), it, isSelected, onClick)
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 24.dp)
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            onClick = {
                onAddItem(
                    GroceryItem(
                        id = "",
                        name = query.orEmpty(),
                        categoryId = selectedId,
                        isPurchased = false,
                        quantity = 1,
                        updatedAt = System.currentTimeMillis()
                    )
                )
                focusManager.clearFocus(force = true)
                keyboardController?.hide()
            },
            enabled = query?.isNotEmpty() == true
        ) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add Item", color = Color.White)
        }
    }
}

fun iconFromName(name: String): ImageVector {
    return when (name) {
        "ic_milk" -> Icons.Default.LocalDrink
        "ic_vegetables" -> Icons.Default.Eco
        "ic_fruits" -> Icons.Default.CrueltyFree
        "ic_bread" -> Icons.Default.BakeryDining
        "ic_meat" -> Icons.Default.Restaurant
        else -> Icons.Default.ShoppingCart
    }
}

@OptIn(ExperimentalUuidApi::class)
@Composable
fun CategoryCardItem(
    modifier: Modifier = Modifier,
    category: Category,
    isSelected: Boolean,
    onClick: (id: String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(category.id)
            },
        colors = CardDefaults.cardColors(
            containerColor =
                Color(category.bgColorHex.toColorInt())
        ),
        border = if (isSelected) BorderStroke(
            2.dp,
            Color(category.accentHex.toColorInt())
        ) else null
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = iconFromName(category.iconKey),
                contentDescription = "",
                tint = Color(category.accentHex.toColorInt())
            )
            Text(
                text = category.name, color = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun CategoryCardPreview() {
    PropFinderTheme {
        CategoryCard(categories = emptyList(), selectedId = "milk", onClick = {}, query = "", onAddItem = {})
    }
}
