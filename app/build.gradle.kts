plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
    kotlin("plugin.serialization") version "2.0.0"
    id("com.google.dagger.hilt.android")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.abisoft.trellox"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.abisoft.trellox"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        dataBinding = true

        viewBinding = true
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.play.services.gcm)
    implementation(libs.support.annotations)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //lottie
    implementation(libs.lottie)
    // Room
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    // Retrofit uchun
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")

// Gson uchun (JSON ma'lumotlarini parse qilish uchun)
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

// Lifecycle komponentlari uchun (ViewModel va LiveData uchun)
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")

// Kotlin Coroutines uchun (asinxronlikni qo'llab-quvvatlash uchun)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// Dagger-Hilt uchun (Agar siz `Dagger-Hilt` dan foydalanayotgan bo'lsangiz)
    implementation ("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")
    kapt("androidx.hilt:hilt-compiler:1.2.0")


    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3") // Eng so'nggi
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.0") // Use the latest version
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.0" )// Use the latest version

    //glid libs
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
}