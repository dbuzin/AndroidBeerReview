plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {

    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.appCompat)

    api(Dependencies.activityCompose)
    api(Dependencies.material)
    api(Dependencies.composeMaterial)
    api("androidx.compose.runtime:runtime-livedata:${Versions.compose}")
    api("androidx.compose.ui:ui:${Versions.compose}")
    api("androidx.compose.ui:ui-tooling:${Versions.compose}")
    api("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")

    api(Dependencies.accompanistSystemUi)
    api(Dependencies.dateTime)
    api(Dependencies.accompanistFlowLayout)

    implementation(Dependencies.accompanistPager)
    implementation(Dependencies.accompanistPagerIndicators)

    implementation(Dependencies.lottie)

    debugImplementation(Dependencies.composeTooling)
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