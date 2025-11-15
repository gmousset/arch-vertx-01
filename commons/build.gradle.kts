plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

val vertxVersion: String by project

dependencies {
    api("io.vertx:vertx-core:$vertxVersion")
    api("io.vertx:vertx-opentelemetry:$vertxVersion")
    api("io.opentelemetry.semconv:opentelemetry-semconv:1.37.0")
    api("io.opentelemetry:opentelemetry-exporter-otlp:1.56.0")
    implementation("ch.qos.logback:logback-classic:1.5.20")
    api("org.slf4j:slf4j-api:2.0.17")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.20.1")
}

tasks.named<Test>("test") {
}
