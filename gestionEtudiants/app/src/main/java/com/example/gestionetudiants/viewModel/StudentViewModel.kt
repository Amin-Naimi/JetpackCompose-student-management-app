package com.example.gestionetudiants.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionetudiants.config.MyApp
import com.example.gestionetudiants.data.AppDatabase
import com.example.gestionetudiants.data.StudentRepository
import com.example.gestionetudiants.data.model.Student
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {

    private val db = AppDatabase.getDatabase(MyApp.instance)
    private val repository = StudentRepository(db.studentDao())

    val allStudents: StateFlow<List<Student>> = repository.getAllStudents()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    var snackbarMessage by mutableStateOf<String?>(null) // Déclarer snackbarMessage ici pour pouvoir l'utiliser dans l'UI

    fun addStudent(student: Student) {
        viewModelScope.launch {
            val existingStudent = repository.getStudentByFullName(student.name, student.lastName)
            if (existingStudent != null) {
                snackbarMessage = "Student with the same name already exists!"
            }
            else {
                repository.insert(student)
                snackbarMessage = "Student added successfully"
            }        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            repository.update(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            repository.delete(student)
        }
    }

    suspend fun getStudentById(studentId: Int): Student? {
        return repository.getStudentById(studentId)
    }

    fun isFormValid(studentName: String, studentLastName: String): Boolean {
        return studentName.isNotBlank() && studentLastName.isNotBlank()
    }
}

/*
* private set: La syntaxe private set est une déclaration d'un getter public avec un setter privé,
            ce qui signifie que la variable peut être lue de l'extérieur de la classe, mais ne peut
        être modifiée qu'à l'intérieur de la classe.*/

/*
*  Points clés
Données actives : Le StateFlow continue à émettre des nouvelles valeurs à tous les collecteurs actifs. Même s'il n'y a pas de collecteur, il peut conserver une valeur actuelle qui est renvoyée dès qu'un collecteur s'abonne.

Données inactives : Une fois que tous les collecteurs se sont désabonnés, et après le délai de 5 secondes (dans l'exemple avec WhileSubscribed(5000)), le StateFlow ne produit plus de nouvelles données. Cela économise des ressources, car il n'émet plus de valeurs sans raison.*/