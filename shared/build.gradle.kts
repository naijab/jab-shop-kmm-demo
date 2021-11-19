import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.5.31"
}

version = "1.0"

val ktorVersion = "1.6.5"
val coroutineVersion = "1.5.2-native-mt"

kotlin {
    android()

    ios()
    iosSimulatorArm64()

    cocoapods {
        summary = "Shared module for Jab Shop"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
                implementation(
                    "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
                implementation(
                    "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.2.0")
                implementation(
                    "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
        val iosTest by getting
        val iosSimulatorArm64Main by getting
        val iosSimulatorArm64Test by getting

        iosSimulatorArm64Main.dependsOn(iosMain)
        iosSimulatorArm64Test.dependsOn(iosTest)
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(31)
    }
}
