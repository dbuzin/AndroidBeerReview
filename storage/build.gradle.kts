plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

dependencies {

    implementation(Dependencies.datastore)
    implementation(Dependencies.cryptoAndroid)

    implementation(Dependencies.hiltAndroid)

    kapt(Dependencies.hiltAndroidCompiler)
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 28
        targetSdk = 31
    }
}