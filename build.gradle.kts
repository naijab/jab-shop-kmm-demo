buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url ="https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}