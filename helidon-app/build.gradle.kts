import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.50"
}

group = "demo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.register("copyDeps") {
    doLast {
        println(project.dependencies)
        tasks.getByName("jar", Jar::class).destinationDirectory.asFile
        project.configurations.getByName("runtimeClasspath").files.forEach {
            it.copyTo(it.name, true)
        }
    }
}