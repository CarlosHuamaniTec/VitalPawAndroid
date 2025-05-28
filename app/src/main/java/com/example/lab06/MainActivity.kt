package com.example.lab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab06.ui.theme.Lab06Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab06Theme {
                MainScreenWithBottomBar()
                //Contador()
            }
        }
    }
}

@Composable
fun MainScreenWithBottomBar() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = { CustomFAB {} },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues).fillMaxSize(), contentAlignment = Alignment.Center) {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen() }
                    composable("build") { BuildScreen() }
                    composable("menu") { MenuScreen() }
                    composable("favorite") { FavoriteScreen() }
                    composable("delete") { DeleteScreen() }
                    composable("profile") { UserProfileScreen() }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {navController.navigate("menu")}) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
            }
        },
        title = { Text(text = "Sample Title") },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
            }
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "Profile")
            }
        }
    )
}

@Composable
fun CustomBottomBar(navController: NavHostController) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { navController.navigate("build") }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Filled.Build, contentDescription = "Build")
        }
        IconButton(onClick = { navController.navigate("menu") }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu")
        }
        IconButton(onClick = { navController.navigate("favorite") }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
        }
        IconButton(onClick = { navController.navigate("delete") }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}
@Composable
fun Contador() {
    var clickCount by remember { mutableStateOf(0) }
    Scaffold(
        topBar = { CustomTopBar(rememberNavController()) },
        floatingActionButton = { CustomFAB { clickCount++ } },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Has apretado el botón $clickCount veces", fontSize = 24.sp)
            }
        }
    )
}

@Composable
fun CustomFAB(onFabClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onFabClick() },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            fontSize = 24.sp,
            text = "+"
        )
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    Lab06Theme {
        MainScreenWithBottomBar()
    }
}
