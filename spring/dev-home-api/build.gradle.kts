description = "Home Module"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("com.google.code.gson:gson:2.8.5")

    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("com.h2database:h2")
    testImplementation("com.h2database:h2")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}