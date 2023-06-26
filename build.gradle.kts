plugins {
    java
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "ru.kpfu.itis.gnt"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    google()
    jcenter()
}


dependencies {

    val springStarterVersion = "3.0.6"
    val lombokVersion = "1.18.26"
    val postgresVersion = "42.6.0"
    val pebbleVersion = "3.2.1"
    val materialUiVersion = "1.3.0"
    val jsoupVersion = "1.7.2"

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    runtimeOnly("org.postgresql:postgresql:$postgresVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springStarterVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springStarterVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springStarterVersion")

    implementation("io.pebbletemplates:pebble-spring-boot-starter:$pebbleVersion")

    implementation("org.webjars:material-design-lite:$materialUiVersion")

    implementation("org.hibernate:hibernate-validator:8.0.0.Final")


    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

    implementation("com.googlecode.json-simple:json-simple:1.1.1")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
