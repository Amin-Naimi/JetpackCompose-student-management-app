package com.example.gestionetudiants.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "student_first_name")val name: String,
    @ColumnInfo(name="student_last_name")val lastName: String,
    @ColumnInfo(name = "student_mark")val mark: Float
)
