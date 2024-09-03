// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}
ext {
    extra["roomVersion"] = "2.5.1"  // Replace with the appropriate version
    extra["lifecycleVersion"] = "2.4.0"
    extra["constraintLayoutVersion"] = "2.1.3"
    extra["materialVersion"] = "1.5.0"
}

buildscript {
    val kotlin_version by extra("2.0.0") // Ensure this is updated to the correct version

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

