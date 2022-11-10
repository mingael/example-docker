import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"

	kotlin("jvm")
	kotlin("plugin.spring") apply false
	kotlin("plugin.allopen") apply false
	kotlin("plugin.noarg") apply false
	kotlin("kapt") apply false
}

allprojects {
	group = "com.example"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply {
		plugin("java")
		plugin("kotlin")
		plugin("kotlin-jpa")
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
	}

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	}

	configure<JavaPluginExtension> {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = JavaVersion.VERSION_17.toString()
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	enabled = false
}

tasks.getByName<Jar>("jar") {
	enabled = false
}

project(":dev-core") {
	apply {
		plugin("kotlin-spring")
		plugin("kotlin-kapt")
	}
}

project(":dev-admin-api") {
	apply {
		plugin("kotlin-spring")
	}
	dependencies {
		implementation(project(":dev-core"))
	}
}

project(":dev-home-api") {
	apply {
		plugin("kotlin-spring")
	}
	dependencies {
		implementation(project(":dev-core"))
	}
}