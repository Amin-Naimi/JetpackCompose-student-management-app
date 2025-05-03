package com.example.gestionetudiants.viewModel

import androidx.lifecycle.ViewModel
import com.example.gestionetudiants.R
import com.example.gestionetudiants.data.model.MenuItem

class MenuViewModel: ViewModel() {
    val menuItemList = listOf(
        MenuItem("Add Student", R.drawable.add),
        MenuItem("Delete Student", R.drawable.delete),
        MenuItem("Update Student", R.drawable.update),
        MenuItem("View Students", R.drawable.view)
    )

}