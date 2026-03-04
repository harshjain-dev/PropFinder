package com.harshjaindev.data.local.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.harshjaindev.data.local.entities.CategoryEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CategorySeedLoader @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {

    fun loadFromAssets(): List<CategoryEntity> {
        val jsonString = context.assets
            .open("grocery_store_seed.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<SeedFile>() {}.type
        val seedFile: SeedFile = gson.fromJson(jsonString, type)

        return seedFile.categories.map {
            CategoryEntity(
                id = it.categoryId,
                name = it.displayName,
                iconKey = it.icon,
                bgColorHex = it.color,
                accentHex = it.accentColor,
                description = it.description,
                isSelected = it.isSelected
            )
        }
    }
}