package com.main.appbookshelfrmv.Core

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
)

data class Book(
    val volumeInfo: VolumeInfo
)

data class BookResponse(
    val items: List<Book>
)