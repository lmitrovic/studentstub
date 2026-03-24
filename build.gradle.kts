plugins {
    id("java-library")
    id("maven-publish")
}

group = "raf.rs"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(17)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("jakarta.activation:jakarta.activation-api:2.1.1")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")

    implementation("org.eclipse.jgit:org.eclipse.jgit:7.5.0.202512021534-r")
    implementation("org.zeroturnaround:zt-zip:1.17")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}