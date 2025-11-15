pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("org.jlleitschuh.gradle.ktlint") version "13.0.0"
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

plugins {}

rootProject.name = "arch-vertx-01"

include("app")
include("commons")
include("http-api-01")
include("service-01")
