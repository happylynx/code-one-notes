import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.Path as JavaPath

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

tasks {
    register<Exec>("nativeImage") {
        dependsOn(jar)
        val commandLine = createNativeImageCommandLine()
        setCommandLine(commandLine)
        doFirst {
            println("Running: ${commandLine.joinToString(" ")}")
        }
    }
}

fun getRuntimeClasspath(): String {
    val pathSeparator = System.getProperty("path.separator")
    val jars = ArrayList<File>()
    jars.add(tasks.jar.get().archiveFile.get().asFile)
    jars.addAll(configurations.getByName("runtimeClasspath").files)
    return jars.joinToString(pathSeparator) { it.canonicalPath }
}

fun createNativeImageCommandLine(): Iterable<String> {
    val fallbackProperty = "fallback"
    val noFallback = if (hasProperty(fallbackProperty)) { property(fallbackProperty) == "no" } else false
    val forceFallback = if (hasProperty(fallbackProperty)) { property(fallbackProperty) == "force" } else false
    val command = ArrayList<String>()
    command.add(getNativeImage().toString())
    command.add("-cp")
    command.add(getRuntimeClasspath())
    if (noFallback) {
        command.add("--no-fallback")
    }
    if (forceFallback) {
        command.add("--force-fallback")
    }
    val nativeImageOptionsProperty = "niOptions"
    if (hasProperty(nativeImageOptionsProperty)) {
        command.addAll((property(nativeImageOptionsProperty) as String).split(" "))
    }
    val mainClassName = property("main") as String
    command.add(mainClassName)
    val appNameProperty = "appName"
    val appNameSuffix = if (forceFallback) "Jvm" else ""
    val appName = if (hasProperty(appNameProperty))
        property(appNameProperty) as String
        else (toAppName(mainClassName) + appNameSuffix)
    val nativeImagesDirectory = buildDir.resolve("native-images")
    nativeImagesDirectory.mkdirs()
    command.add(nativeImagesDirectory.resolve(appName).canonicalPath)
    return command
}

fun toAppName(fqcn: String): String {
    val className = fqcn.split('.').last()
    return toLowerCamelCase(className)
}

fun toLowerCamelCase(upperCamelCase: String): String {
    return upperCamelCase[0].toLowerCase().toString() +
        (if (upperCamelCase.length > 1) upperCamelCase.substring(1) else "")
}

fun getNativeImage(): JavaPath {
    val graalVmHome = "GRAALVM_HOME"
    if (!System.getenv().containsKey(graalVmHome)) {
        throw GradleException("Environment variable '$graalVmHome' not defined.")
    }
    val nativeImageSuffix = if (System.getProperty("os.name").contains("Windows")) ".cmd" else ".sh"
    val nativeImage = Paths.get(System.getenv()[graalVmHome]!!, "bin", "native-image${nativeImageSuffix}")
    if (!Files.isExecutable(nativeImage)) {
        throw GradleException("'$nativeImage' cannot be executed.")
    }
    return nativeImage
}