plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.10"
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// developmentOnly
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// jwt
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

	// security
	implementation("org.springframework.boot:spring-boot-starter-security")

	// web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// lombok
	compileOnly("org.projectlombok:lombok")

	// database
	implementation("mysql:mysql-connector-java:8.0.28")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	// jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// annotation
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	// mail
	implementation("org.springframework.boot:spring-boot-starter-mail:3.1.2")

	// swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")

	// firebase
	implementation("com.google.firebase:firebase-admin:9.2.0")

	// aws s3
	implementation("software.amazon.awssdk:s3:2.25.16")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
