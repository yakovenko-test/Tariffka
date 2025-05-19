plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)

    application
}

group = "test.yakovenko"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.config.yaml)

    implementation(libs.logback.classic)

    testImplementation(libs.ktor.server.test.host)
}

kotlin {
    jvmToolchain(21)
}
