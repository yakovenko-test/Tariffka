plugins {
    alias(libs.plugins.kotlin.jvm)
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("-XX:+EnableDynamicAgentLoading")
}

dependencies {
    implementation(project(":common"))

    implementation(libs.kotlinx.datetime)

    implementation(libs.dagger)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockk)
}
