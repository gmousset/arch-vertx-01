plugins {
    kotlin("jvm")
}

val vertxVersion: String by project

dependencies {
    api("io.vertx:vertx-core:$vertxVersion")
    implementation("ch.qos.logback:logback-classic:1.5.20")
    api("org.slf4j:slf4j-api:2.0.17")
}

tasks.named<Test>("test") {
}
