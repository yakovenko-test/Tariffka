plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "test.yakovenko"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}