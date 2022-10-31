plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.design))

    debugImplementation(Dependencies.composeTooling)

    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.hiltNavigation)
    implementation(Dependencies.hiltViewModel)
    implementation(Dependencies.navigation)

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
        buildConfig = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}