package com.example.gestionetudiants.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.gestionetudiants.ui.composables.CommonTopAppBar
import com.example.gestionetudiants.ui.composables.StudentItem
import com.example.gestionetudiants.viewModel.StudentViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StudentListScreen(viewModel: StudentViewModel, navController: NavController) {
    val students by viewModel.allStudents.collectAsState()

    Scaffold(
        containerColor = Color(0xFFF0F2F5),
        topBar = { CommonTopAppBar( navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp, start = 8.dp)
        ) {
            Text(
                text = "Students List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (students.isEmpty()) {
                Text("No students found.")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(students) { student ->
                        StudentItem(student, navController)
                    }
                }
            }
        }
    }
}