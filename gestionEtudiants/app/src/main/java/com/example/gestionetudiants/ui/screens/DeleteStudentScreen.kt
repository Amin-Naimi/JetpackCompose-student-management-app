package com.example.gestionetudiants.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.gestionetudiants.data.model.Student
import com.example.gestionetudiants.ui.composables.CommonTopAppBar
import com.example.gestionetudiants.viewModel.StudentViewModel

@Composable
fun DeleteStudentScreen(viewModel: StudentViewModel, navController: NavController) {
    var studentId by remember { mutableStateOf("") }
    var studentToShow by remember { mutableStateOf<Student?>(null) }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage = null
        }
    }

    LaunchedEffect(studentId) {
        val id = studentId.toIntOrNull()
        studentToShow = id?.let { viewModel.getStudentById(it) }
    }

    Scaffold(
        containerColor = Color(0xFFF0F2F5),
        topBar = { CommonTopAppBar(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Delete Student",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = studentId,
                    onValueChange = { studentId = it },
                    label = { Text("Student ID") },
                    modifier = Modifier.fillMaxWidth()
                )

                studentToShow?.let { student ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Student Icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "${student.name} ${student.lastName}")
                    }
                } ?: run {
                    if (studentId.isNotEmpty()) {
                        Text(
                            text = "No student found with this ID",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    enabled = studentToShow != null
                ) {
                    Text(text = "Delete Student", fontSize = 18.sp)
                }
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Confirm Deletion") },
                    text = { Text("Are you sure you want to delete ${studentToShow?.name} ${studentToShow?.lastName}?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                studentToShow?.let {
                                    viewModel.deleteStudent(it)
                                    snackbarMessage = "Student deleted successfully"
                                    showDialog = false
                                }
                            }
                        ) {
                            Text("Delete")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}
