plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "br.com.thecoders"
version = "1.0.0"
description = "Add to your project the ability to easily display customized and formatted datetime notation."

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<Jar> {
    archiveBaseName.set("TimeNote")
    archiveVersion.set("$version")
    archiveClassifier.set("")
}
