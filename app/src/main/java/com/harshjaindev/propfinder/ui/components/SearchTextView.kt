package com.harshjaindev.propfinder.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harshjaindev.propfinder.ui.theme.PropFinderTheme

@Composable
fun SearchTextView(
    modifier: Modifier = Modifier,
    onQueryChanged: (query: String) -> Unit,
    query: String?
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            text = "Item Name",
            fontSize = 20.sp,
            color = Color.Black
        )
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = query.orEmpty(),
            placeholder = {
                Text("Search Items to add...")
            },
            onValueChange = {
                Log.d("ValueChanged", it)
                onQueryChanged(it)
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black
            )
        )
    }
}

@Preview
@Composable
private fun SearchTextViewPreview() {
    PropFinderTheme {
        SearchTextView(
            query = "",
            onQueryChanged = {

            },
        )
    }
}
