package com.example.gestionetudiants.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gestionetudiants.data.model.Student


@Database(entities = [Student::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun studentDao():StudentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "student_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

/*Notes:

* Note sur la génération automatique par Room
    Lorsque vous définissez une base de données Room, vous créez une classe abstraite qui étend RoomDatabase
    (par exemple, AppDatabase). Cette classe contient des méthodes abstraites qui déclarent les DAO (Data Access Objects)
    utilisés pour interagir avec la base de données.

*Pourquoi la classe AppDatabase est abstraite ?:
        Room génère une implémentation concrète de la classe AppDatabase au moment de la compilation.
        Vous ne devez pas implémenter cette classe vous-même, Room s'en charge pour vous.
        Cette classe concrète est responsable de l'accès à la base de données et de l'initialisation des DAO.

* Pourquoi les méthodes dans AppDatabase sont abstraites ?:
        Les méthodes abstraites dans AppDatabase, comme studentDao(), déclarent l'interface pour accéder aux DAO.
        Room va automatiquement générer des implémentations concrètes de ces méthodes, qui retourneront des instances des DAO nécessaires
        pour effectuer des opérations sur la base de données (ajout, mise à jour, suppression, recherche, etc.).

* Exemple de génération de Room :
    Lorsque vous définissez un DAO comme StudentDao, Room crée une classe concrète, par exemple StudentDao_Impl, qui implémente les méthodes de ce DAO.
    La méthode abstraite studentDao() dans AppDatabase sera automatiquement implémentée par Room, créant ainsi une instance de StudentDao_Impl.

* En résumé : Room simplifie l'accès à la base de données en générant automatiquement des classes concrètes pour
gérer l'implémentation de la base de données et des DAO. Vous définissez les entités et les DAO,
et Room s'occupe de la gestion des détails d'implémentation, ce qui réduit le code boilerplate et le risque d'erreurs.
*/


/*
* Notes:
* Note: If your app runs in a single process, you should follow the singleton design pattern when instantiating an AppDatabase object.
* Each RoomDatabase instance is fairly expensive, and you rarely need access to multiple instances within a single process.
*  */

/*
* Ce fichier permet de centraliser et d'initialiser la connexion à la base de données Room. Il sert à instancier la base de données Room et à fournir une référence unique à la base de données.
Rôle principal : Créer et fournir une instance unique de la base de données (singleton).
Il assure que vous n'avez qu'une seule instance de AppDatabase dans l'application pour éviter d'en créer plusieurs.

* @Volatile : Cette annotation garantit que les modifications de la variable instance sont visibles de manière cohérente entre tous les threads.

instance ?: synchronized(this) : Si l'instance de DatabaseClient est déjà créée, elle est retournée. Sinon, un bloc synchronized garantit que l'instance est créée de manière sûre même dans un environnement multi-thread (par exemple, si plusieurs threads essaient d'accéder à getInstance simultanément).

also { instance = it } : Après avoir créé l'instance, elle est assignée à instance, garantissant qu'elle ne sera créée qu'une seule fois.


En Kotlin, un companion object est un objet qui est associé à une classe et qui permet de définir des membres (comme des propriétés ou des méthodes) qui sont accessibles de manière statique, c'est-à-dire sans avoir besoin de créer une instance de la classe.

Dans l'exemple que tu as partagé, voici ce qui se passe :

Définition du companion object :

Le companion object est une sorte de "bloc statique" qui appartient à la classe. Les membres définis à l'intérieur sont accessibles par le nom de la classe elle-même.

Propriété instance :

instance est une variable privée et mutable qui contient l'instance unique de la classe DatabaseClient. Elle est initialisée à null au départ.

La méthode getInstance :

C'est une méthode qui renvoie l'instance unique de la classe DatabaseClient. Si l'instance n'a pas encore été créée (si elle est null), la méthode la crée de manière sécurisée dans un bloc synchronized (pour garantir que l'instance ne sera pas créée plusieurs fois en même temps si plusieurs threads appellent getInstance).

Utilisation du mot-clé @Volatile :

Cette annotation garantit que les modifications de la variable instance seront visibles par tous les threads immédiatement. Cela empêche que l'instance soit "mise en cache" de manière incorrecte par un thread, ce qui peut être problématique dans un environnement multi-thread.

Lazy Initialization (initialisation paresseuse) :

Le pattern utilisé ici est celui de l'initialisation paresseuse (Lazy initialization), où l'instance est créée uniquement quand elle est réellement nécessaire, et ce de manière thread-safe.

En résumé, ce companion object définit un singleton pour DatabaseClient, garantissant que seule une instance de cette classe existe, même dans un environnement multi-thread.

* */