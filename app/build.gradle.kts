plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.appCompat)

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}")

    implementation(project(Modules.design))

    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.navigation)

    kapt(Dependencies.hiltAndroidCompiler)
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "dev.dbuzin.android.beerreview"
        minSdk = 28
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}