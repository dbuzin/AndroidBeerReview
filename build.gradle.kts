buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.2")
        classpath(Classpath.hilt)
        classpath(Classpath.gradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}