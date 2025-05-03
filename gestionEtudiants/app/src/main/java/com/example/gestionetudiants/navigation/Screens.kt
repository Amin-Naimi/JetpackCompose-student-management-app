package com.example.gestionetudiants.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object AddStudent : Screen("add_student")
    object DeleteStudent : Screen("delete_student")
    object UpdateStudent : Screen("update_student")
    object UpdateStudentWithId : Screen("update_student/{studentId}")
    object StudentList : Screen("student_list")
}


/*
Notes:
  * sealed class : une classe scellée permet de définir un ensemble fermé de sous-classes.
        Cela signifie que toutes les classes qui héritent de Screen sont connues à
        la compilation et doivent être définies dans le même fichier.

   *object:
        Ce sont des objets singleton héritant de Screen.
        Chacun représente un écran spécifique dans l'application.
* */


/*
* En Kotlin, les sealed class sont simples, puissantes et très utilisées dans la construction de systèmes fermés (comme des états, des erreurs, ou ici… des écrans).*/