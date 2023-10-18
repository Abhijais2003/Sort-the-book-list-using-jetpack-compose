package com.abhishek_jaiswal.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent() {
    var mDisplayMenu by remember { mutableStateOf(false) }
    var books by remember { mutableStateOf(sampleBooks) }
    val mContext = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sort the books | Abhishek Jaiswal", color = Color.Black) },
                backgroundColor = Color(0xFFF0D5D5),
                actions = {
                    IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                        Icon(Icons.Default.MoreVert, "")
                    }
                    DropdownMenu(
                        expanded = mDisplayMenu,
                        onDismissRequest = { mDisplayMenu = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            books = books.sortedBy { it.name }
                            mDisplayMenu = false
                            Toast.makeText(mContext, "Sorting A to Z", Toast.LENGTH_SHORT).show()
                        }) {
                            Text(text = "A to Z")
                        }
                        DropdownMenuItem(onClick = {
                            books = books.sortedByDescending { it.name }
                            mDisplayMenu = false
                            Toast.makeText(mContext, "Sorting Z to A", Toast.LENGTH_SHORT).show()
                        }) {
                            Text(text = "Z to A")
                        }
                    }
                }
            )
        },
        content = {
            LazyColumn {
                itemsIndexed(books) { index, book ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${index + 1}.",
                            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(start=16.dp, top=15.dp)
                        )
                        Image(
                            painter = painterResource(id = book.imageResId),
                            contentDescription = null,
                            modifier = Modifier.size(120.dp).padding(start=8.dp, top=15.dp)
                        )
                        Text(
                            text = book.name,
                            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(start=8.dp, top=15.dp)
                        )
                    }
                }
            }
        }
    )
}

data class Book(val name: String, val imageResId: Int)

val sampleBooks = listOf(
    Book("Java book", R.drawable.java),
    Book("DSA book", R.drawable.dsa),
    Book("C++ book", R.drawable.cplusplus),
    Book("Python book", R.drawable.python),
    Book("Kotlin book", R.drawable.kotlin),
    Book("DBMS book", R.drawable.sql),
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent()
}
