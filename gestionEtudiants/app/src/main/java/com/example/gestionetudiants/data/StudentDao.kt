package com.example.gestionetudiants.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gestionetudiants.data.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM students")
     fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM students WHERE uid = :uid LIMIT 1")
    suspend fun findStudentById(uid: Int): Student?

    @Query("SELECT * FROM students WHERE student_first_name = :name AND student_last_name = :lastName LIMIT 1")
    suspend fun getStudentByFullName(name: String, lastName: String): Student?
    @Insert
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

}