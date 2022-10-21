plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization").version(Versions.serialization)
}

dependencies {
    implementation(project(Modules.storage))

    implementation(Dependencies.serialization)

    implementation(Dependencies.ktor)
    implementation(Dependencies.ktorAndroid)
    implementation(Dependencies.ktorCIO)
    implementation(Dependencies.ktorAuth)
    implementation(Dependencies.ktorSerialization)
    implementation(Dependencies.ktorNegotiation)
    implementation(Dependencies.ktorLogging)

    implementation(Dependencies.hiltAndroid)

    kapt(Dependencies.hiltAndroidCompiler)
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