plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

val vertxVersion: String by project

dependencies {
    implementation(project(":commons"))
    implementation("io.vertx:vertx-web:$vertxVersion")
}

tasks.named<Test>("test") {
}
