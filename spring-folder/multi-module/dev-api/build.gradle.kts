description = "Api Module"

dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.getByName<Jar>("jar") {
    enabled = true
}