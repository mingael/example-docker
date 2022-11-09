description = "Api Module"

dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.getByName<Jar>("jar") {
    enabled = true
}