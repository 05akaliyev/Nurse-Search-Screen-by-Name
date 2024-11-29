package com.example.searchnursesscreenbyname

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.searchnursesscreenbyname.ui.theme.SearchNursesScreenByNameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SearchNursesScreenByNameTheme {
                FindNursesByNameScreen()
            }
        }
    }
}

class Nurse(var user: String, var password: String)

val nurses = ArrayList<Nurse>().apply {
    add(Nurse("Alberto", "password1"))
    add(Nurse("Maria", "password2"))
    add(Nurse("Juan", "password3"))
}

@Composable
fun FindNursesByNameScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val filteredNurses = nurses.filter { it.user.contains(searchQuery, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Find Nurse by Name", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredNurses.take(3)) { nurse ->
                Text(text = nurse.user, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Preview
@Composable
fun PreviewFindNursesByNameScreen() {
    SearchNursesScreenByNameTheme {
        FindNursesByNameScreen()
    }
}
