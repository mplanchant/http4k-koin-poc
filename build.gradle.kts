import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.5.20"
}

group = "uk.co.logiccache"
version = "1.0-SNAPSHOT"
val koinVersion = "3.1.0"

application {
    mainClass.set("uk.co.logiccache.petstore.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.20")
    implementation(platform("org.http4k:http4k-bom:4.9.8.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-netty")
    implementation("org.http4k:http4k-client-apache")
    implementation("org.http4k:http4k-format-jackson")
    testImplementation(kotlin("test"))
    implementation("io.insert-koin:koin-core:$koinVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}