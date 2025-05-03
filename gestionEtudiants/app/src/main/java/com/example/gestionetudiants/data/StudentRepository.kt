package com.example.gestionetudiants.data

import com.example.gestionetudiants.data.model.Student
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {


    fun getAllStudents(): Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(user: Student) {
        studentDao.insertStudent(user)
    }

    suspend fun update(user: Student) {
        studentDao.updateStudent(user)
    }

    suspend fun delete(user: Student) {
        studentDao.deleteStudent(user)
    }

    suspend fun getStudentById(uid: Int): Student? {
        return studentDao.findStudentById(uid)
    }
    suspend fun getStudentByFullName(name: String, lastName: String): Student? {
        return studentDao.getStudentByFullName(name, lastName)
    }
}

/* Notes;
Peut-on écrire suspend fun getAllStudents(): Flow<List<Student>> ?
Oui, techniquement on peut, mais ce n’est pas nécessaire et même pas recommandé ici.

🔹 Flow est "froid" : il ne fait rien tant qu’on ne le collecte pas.

🔹 Ce n’est pas une opération qui suspend : la fonction ne bloque pas, elle retourne immédiatement un objet Flow.
* */