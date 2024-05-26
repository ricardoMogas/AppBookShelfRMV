// MainActivity.kt
package com.main.appbookshelfrmv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.main.appbookshelfrmv.Core.VolumeInfo
import com.main.appbookshelfrmv.Core.ImageLinks
import coil.compose.rememberImagePainter
import com.main.appbookshelfrmv.Core.Book
import com.main.appbookshelfrmv.ui.theme.AppBookShelfRMVTheme
import com.main.appbookshelfrmv.Core.BookViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchBooks("jazz history")
        setContent {
            AppBookShelfRMVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BookList(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun BookList(viewModel: BookViewModel) {
    val books by viewModel.books.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(books) { book ->
            BookItem(book = book)
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Column(modifier = Modifier.padding(8.dp)) {
        book.volumeInfo.imageLinks?.thumbnail?.let { url ->
            Image(
                painter = rememberAsyncImagePainter(model = url),
                contentDescription = null,
                modifier = Modifier.height(150.dp).fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = book.volumeInfo.title)
        Text(text = book.volumeInfo.authors?.joinToString(", ") ?: "")
    }
}

@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
    val sampleBook = Book(
        volumeInfo = VolumeInfo(
            title = "Sample Book",
            authors = listOf("Author 1", "Author 2"),
            publisher = "Sample Publisher",
            publishedDate = "2022-01-01",
            description = "This is a sample book description.",
            imageLinks = ImageLinks(
                smallThumbnail = "http://books.google.com/books/content?id=C1MI_4nZyD4C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
                thumbnail = "http://books.google.com/books/content?id=C1MI_4nZyD4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
            )
        )
    )
    AppBookShelfRMVTheme {
        BookItem(book = sampleBook)
    }
}
