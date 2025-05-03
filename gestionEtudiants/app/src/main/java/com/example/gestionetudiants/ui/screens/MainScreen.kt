package com.example.gestionetudiants.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gestionetudiants.navigation.Screen
import com.example.gestionetudiants.ui.composables.CommonTopAppBar
import com.example.gestionetudiants.ui.composables.MenuActionItemCard
import com.example.gestionetudiants.viewModel.MenuViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MenuViewModel, navController: NavController) {
    val menuItems = viewModel.menuItemList

    Scaffold(
        containerColor = Color(0xFFF0F2F5),
        topBar = { CommonTopAppBar(navController, showBackArrow = false) }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = 150.dp,
                bottom = 32.dp
            )
        ) {
            items(menuItems) { menuItem ->
                MenuActionItemCard(menuItem) {
                    when (menuItem.title) {
                        "Add Student" -> navController.navigate(Screen.AddStudent.route)
                        "Delete Student" -> navController.navigate(Screen.DeleteStudent.route)
                        "Update Student" -> navController.navigate(Screen.UpdateStudent.route)
                        "View Students" -> navController.navigate(Screen.StudentList.route)
                    }
                }
            }
        }
    }
}


/*
* // ️ on Ne pas utiliser Modifier.background ici.
                // Les AppBars de Material3 ont leur propre système de couleurs.
                // Utiliser plutôt TopAppBarDefaults.centerAlignedTopAppBarColors
                // pour définir la couleur d’arrière-plan (containerColor).
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFC8D0D9)
                )*/