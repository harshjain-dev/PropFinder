package com.harshjaindev.propfinder.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harshjaindev.propfinder.R
import com.harshjaindev.propfinder.ui.theme.PropFinderTheme

@Composable
fun BrandCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(top = 16.dp),
    ) {
        Icon(
            modifier = Modifier.size(100.dp).align(Alignment.CenterHorizontally),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_grocery_hero),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            text = "Grocery List",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp)
        )
        Text(
            text = "Add Items to your Shopping List",
            fontSize = 14.sp,
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
private fun BrandCardPreview() {
    PropFinderTheme {
        BrandCard()
    }
}