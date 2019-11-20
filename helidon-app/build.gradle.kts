import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.60"
}

group = "demo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// List dependencies: gradlew -q dependencies --configuration compileClasspath
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(enforcedPlatform("io.helidon:helidon-bom:1.3.1"))
    implementation(group = "io.helidon.webserver", name = "helidon-webserver")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.register("printClasspath") {
    doLast {
        val classpath = tasks.getByName<Jar>("jar")
            .destinationDirectory
            .asFile
            .get()
            .listFiles()!!
            .joinToString(separator = System.getProperty("path.separator")) { it.canonicalPath }
        println(classpath)
    }
    dependsOn("copyDeps")
}

tasks.register<Copy>("copyDeps") {
    into(tasks.getByName("jar", Jar::class).destinationDirectory)
    from(project.configurations.getByName("runtimeClasspath"))
    dependsOn("jar")
}