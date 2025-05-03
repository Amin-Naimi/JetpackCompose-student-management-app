package com.example.gestionetudiants

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.gestionetudiants.navigation.StudentApp
import com.example.gestionetudiants.ui.theme.GestionEtudiantsTheme
import com.example.gestionetudiants.viewModel.MenuViewModel
import com.example.gestionetudiants.viewModel.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]
        val viewModel = ViewModelProvider(this)[MenuViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            GestionEtudiantsTheme {
                StudentApp(
                    menuViewModel = viewModel,
                    studentViewModel = studentViewModel
                )
            }
        }
    }
}


