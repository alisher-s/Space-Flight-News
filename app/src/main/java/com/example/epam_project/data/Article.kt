package com.example.epam_project.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String
)
