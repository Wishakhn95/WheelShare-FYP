plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.squareup.sqldelight")
    id("com.android.library")
    id("kotlin-parcelize")
    id("org.jetbrains.compose")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            isStatic = true
            baseName = "shared"
        }
//        extraSpecAttributes["resources"] =
//            "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.image.loader)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.tabNavigator)
                implementation(libs.voyager.bottomSheetNavigator)
                implementation(libs.voyager.transitions)

                implementation(libs.compose.util)

                implementation(libs.kotlin.serialization)
                implementation(libs.kotlinx.datetime)

                implementation(libs.auth.live)
                implementation(libs.config.live)
                implementation(libs.firestore.live)

                implementation(libs.koin.core)
                implementation(libs.koin.compose.mp)

                implementation(libs.kotlinx.coroutines.core)

                implementation(libs.islandtime)

                implementation(libs.sql.common.main)

                api(libs.kmm.viewmodel)

                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.animation)
                // Workaround as per https://youtrack.jetbrains.com/issue/KT-41821
                implementation(libs.atomicfu)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.koin.compose)
                implementation(libs.sql.android.main)
                implementation(libs.lifecycle.runtime.compose)
                api(libs.androidx.activity.compose)
                api(libs.androidx.appcompat)
                api(libs.core.ktx)
                implementation(libs.camera.lifecycle)
                implementation(libs.camera.extension)
                implementation(libs.camera.view)
                implementation(libs.barcode.scanner)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependencies {
                implementation(libs.sql.ios.main)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

sqldelight { database("WheelShareClientDatabase") { packageName = "com.example.shared.database" } }

android {
    namespace = "com.example.wheelshare_client"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}