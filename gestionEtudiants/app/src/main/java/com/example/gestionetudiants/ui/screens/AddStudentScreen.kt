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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.gestionetudiants.data.model.Student
import com.example.gestionetudiants.ui.composables.CommonTopAppBar
import com.example.gestionetudiants.viewModel.StudentViewModel

@Composable
fun AddStudentScreen(viewModel: StudentViewModel, navController: NavController) {
    var studentName by remember { mutableStateOf("") }
    var studentLastName by remember { mutableStateOf("") }
    var studentMark by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.snackbarMessage) {
        viewModel.snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.snackbarMessage = null
        }
    }

    Scaffold(
        containerColor = Color(0xFFF0F2F5),
        topBar = { CommonTopAppBar(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(paddingValues),) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Add New Student",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    label = { Text("First Name") },
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
                        val mark = studentMark.toFloatOrNull() ?: 0f
                        viewModel.addStudent(
                            Student(
                                name = studentName,
                                lastName = studentLastName,
                                mark = mark
                            )
                        )
                        studentName = ""
                        studentLastName = ""
                        studentMark = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    enabled = viewModel.isFormValid(studentName, studentLastName)
                ) {
                    Text(text = "Add Student", fontSize = 18.sp)
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


@Preview(showBackground = true)
@Composable
fun PreviewAddStudentScreen() {
}


/*
* Exemple des formulaire sans ViewModel :

  var name by remember { mutableStateOf("") }  // Conserve l'état local de la variable 'name' pendant les recompositions
      OutlinedTextField(
            value = name,
            onValueChange = { name = it },  // Met à jour la variable 'name'
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth()
        )
* mutableStateOf(): creer une variable d'etat mutable que tu veux pouvoir modifier, et observer ces changements dans l’interface utilisateur.
                       L'état est géré via mutableStateOf()

* remember: est utilisé pour retenir l’état d'une variable pendant les recompositions des composables:
                   C'est utile pour garder l’état local d’une interface (mémoriser la valeur de l'état entre les recompositions du composable)

* by: by est utilisé pour déléguer la gestion de l'état à une fonction comme mutableStateOf().
        Cela permet de simplifier la syntaxe de l'état. Tu n'as pas besoin de gérer explicitement les getters et setters.

* Exemple : var name by remember { mutableStateOf(") } signifie que "name" est géré par mutableStateOf,
            et tout changement de name entraîne une mise à jour automatique de l'UI.

* Différence entre une "variable classique" et une "variable d'état":
        Une variable classique (par exemple var name = "") ne déclenche pas la mise à jour de l'UI lorsque sa valeur change.
        Une variable d'état (créée avec mutableStateOf()) déclenche une recomposition automatique de l'UI chaque fois que la valeur change, ce qui est essentiel dans un framework réactif comme Jetpack Compose.
        L'état préserve sa valeur même entre les recompositions grâce à remember.

* Recomposition de l'UI:
        Jetpack Compose utilise la notion de recomposition, où l'UI est redessinée uniquement
        lorsque l'état observé change. Cela permet d'optimiser les performances.
        Une variable d'état agit comme un signal pour la recomposition de l'UI


*
* */