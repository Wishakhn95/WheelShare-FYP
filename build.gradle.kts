plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.1.0").apply(false)
    id("com.android.library").version("8.1.0").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    kotlin("plugin.serialization").version("1.8.21").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        classpath(libs.kotlinGradle)
        classpath(libs.composeGradle)
        classpath(libs.androidGradle)
        classpath(libs.gms.services)
        classpath(libs.sql.gradle.plugin)
    }

}

allprojects {

    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }
}
