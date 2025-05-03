package com.example.gestionetudiants.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gestionetudiants.data.model.Student
import com.example.gestionetudiants.ui.composables.CommonTopAppBar
import com.example.gestionetudiants.viewModel.StudentViewModel

@Composable
fun UpdateStudentScreen(viewModel: StudentViewModel, navController: NavController, id:String?) {
    var studentId by remember { mutableStateOf(id ?: "") }  // Utilise l'ID passé en paramètre s'il existe
    var studentToUpdate by remember { mutableStateOf<Student?>(null) }
    var studentName by remember { mutableStateOf("") }
    var studentLastName by remember { mutableStateOf("") }
    var studentMark by remember { mutableStateOf("") }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(studentId) {
        val idInt = studentId.toIntOrNull()
        studentToUpdate = idInt?.let { viewModel.getStudentById(it) }
        studentToUpdate?.let {
            studentName = it.name
            studentLastName = it.lastName
            studentMark = it.mark.toString()
        }
    }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage = null
        }
    }

    Scaffold(
        containerColor = Color(0xFFF0F2F5),
        topBar = { CommonTopAppBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Update Student",
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

                studentToUpdate?.let { student ->
                    OutlinedTextField(
                        value = studentName,
                        onValueChange = { studentName = it },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = studentLastName,
                        onValueChange = { studentLastName = it },
                        label = { Text("Last Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = studentMark,
                        onValueChange = { studentMark = it },
                        label = { Text("Mark") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            val updatedStudent = student.copy(
                                name = studentName,
                                lastName = studentLastName,
                                mark = studentMark.toFloatOrNull() ?: 0f
                            )
                            viewModel.updateStudent(updatedStudent)
                            snackbarMessage = "Student updated successfully"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        enabled = studentName.isNotBlank() && studentLastName.isNotBlank()
                    ) {
                        Text(text = "Update Student", fontSize = 18.sp)
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
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}
