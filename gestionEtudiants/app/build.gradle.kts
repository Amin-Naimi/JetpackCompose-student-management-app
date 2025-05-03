plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.gestionetudiants"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gestionetudiants"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    //optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)
    // New icones
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.navigation:navigation-compose:2.7.5")





}

/*Notes:
*  ⚙️ Si ton projet utilise du code Kotlin, tu dois utiliser le KSP (Kotlin Symbol Processing) pour générer automatiquement le code lié aux @Dao, @Entity, etc.
*
* Une coroutine en Kotlin, c’est une manière simple, légère et efficace d’écrire du code asynchrone sans se compliquer la vie avec des threads ou des callbacks.
* Disons que tu veux lire des données depuis une base de données ou une API (opération lente). Sans coroutine, tu aurais besoin de trucs comme des AsyncTask ou Thread, ce qui rend le code compliqué à gérer.

Avec les coroutines, tu écris ton code comme s’il était synchrone, mais il s’exécute de manière asynchrone (sans bloquer l’interface utilisateur).
* */