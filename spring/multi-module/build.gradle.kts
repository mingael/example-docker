import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5" apply false
    id("io.spring.dependency-management") version "1.0.15.RELEASE" apply false
    war

    kotlin("jvm")
    kotlin("plugin.spring") apply false
    kotlin("plugin.allopen") apply false
    kotlin("plugin.noarg") apply false
    kotlin("kapt") apply false
}

// 모든 모듈에 적용
allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

// 프로젝트 수준 build.gradle 을 제외한 모든 하위 모듈에 적용
subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java.sourceCompatibility = JavaVersion.VERSION_17

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

// 특정 모듈에 적용
project(":dev-core") {
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-kapt")
}

project(":dev-api") {
    apply(plugin = "kotlin-spring")
}