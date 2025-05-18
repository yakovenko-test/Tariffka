plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(libs.dagger)

    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.jetbrains.exposed.kotlin.datetime)
}
