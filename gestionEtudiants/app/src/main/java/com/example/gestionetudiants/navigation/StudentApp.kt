package com.example.gestionetudiants.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestionetudiants.ui.screens.*
import com.example.gestionetudiants.viewModel.MenuViewModel
import com.example.gestionetudiants.viewModel.StudentViewModel

@Composable
fun StudentApp(
    menuViewModel: MenuViewModel,
    studentViewModel: StudentViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main.route) {

        composable(Screen.Main.route) {
            MainScreen(viewModel = menuViewModel, navController = navController)
        }
        composable(Screen.AddStudent.route) {
            AddStudentScreen(viewModel = studentViewModel,navController = navController)
        }
        composable(Screen.DeleteStudent.route) {
            DeleteStudentScreen(viewModel = studentViewModel, navController = navController)
        }

        composable(Screen.UpdateStudent.route) {
            UpdateStudentScreen(viewModel = studentViewModel, navController = navController, id = null)
        }

        // Route avec ID d'étudiant
        composable(Screen.UpdateStudentWithId.route) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId")
            UpdateStudentScreen(viewModel = studentViewModel, navController = navController, id = studentId)
        }
        composable(Screen.StudentList.route) {
            StudentListScreen(viewModel = studentViewModel, navController = navController)
        }
    }
}