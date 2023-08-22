plugins {
    id("org.springframework.boot") version "3.0.4"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("com.google.code.gson:gson")

    // GET Library - AdEvent, ClickEvent
    implementation("com.amazonaws:aws-java-sdk-dynamodb:1.12.533")
    implementation("io.github.boostchicken:spring-data-dynamodb:5.2.5")

    // AWS SDK Kotlin - ScrollEvent
    implementation("aws.sdk.kotlin:dynamodb-jvm:0.21.4-beta")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")

    // AWS SDK JAVA - ButtonEvent
    implementation("software.amazon.awssdk:dynamodb-enhanced")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
}
// AWS SDK JAVA - ButtonEvent
dependencyManagement {
    imports {
        mavenBom("software.amazon.awssdk:bom:2.20.26")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
