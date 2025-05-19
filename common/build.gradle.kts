plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "test.yakovenko"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinx.datetime)
}

kotlin {
    jvmToolchain(21)
}