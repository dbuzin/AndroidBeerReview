plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    implementation(project(Modules.design))

    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.ktor)

    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.appCompat)
    api("androidx.compose.runtime:runtime-livedata:${Versions.compose}")

    api("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    api("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}")
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 28
        targetSdk = 31
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}