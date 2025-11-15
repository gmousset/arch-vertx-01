plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

val vertxVersion: String by project

dependencies {
    implementation(project(":commons"))
}

tasks.named<Test>("test") {
}
