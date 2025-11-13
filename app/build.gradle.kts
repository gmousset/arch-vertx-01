plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation("io.netty:netty-resolver-dns-native-macos:4.2.7.Final:osx-aarch_64")
    implementation(project(":commons"))
    implementation(project(":http-api-01"))
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "com.github.gmousset.arch.vertx.t01.MainKt"
}

tasks.named<Test>("test") {
}
