plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "test.yakovenko"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))

    implementation(libs.kotlinx.datetime)

    implementation(libs.dagger)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}

tasks.test {
    useJUnitPlatform()
    jvmArgs(
        "-XX:+EnableDynamicAgentLoading",
        "-Xshare:off"
    )
}

kotlin {
    jvmToolchain(21)
}
