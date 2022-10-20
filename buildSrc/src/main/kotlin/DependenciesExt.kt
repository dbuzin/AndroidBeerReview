import org.gradle.api.artifacts.ConfigurationContainer

fun ConfigurationContainer.kaptHilt() {
    getByName("kapt").dependencies.add(
        org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
            "com.google.dagger",
            "hilt-android-compiler",
            Versions.hilt
        )
    )
}
