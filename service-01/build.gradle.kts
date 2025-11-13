plugins {
    kotlin("jvm")
}

val vertxVersion: String by project

dependencies {
    implementation(project(":commons"))
}

tasks.named<Test>("test") {
}
