import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.3"
	id("org.liquibase.gradle") version "2.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
	jacoco
}

group = "com.spc"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    implementation("org.springframework.boot:spring-boot-starter-security")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "mockito-core")
	}
	testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
	testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("org.springframework.security:spring-security-test")

	liquibaseRuntime("org.liquibase:liquibase-core:4.24.0")
	liquibaseRuntime("info.picocli:picocli:4.7.5")
	liquibaseRuntime("org.yaml:snakeyaml:2.2")
	liquibaseRuntime("org.liquibase.ext:liquibase-hibernate5:4.25.0")
	liquibaseRuntime("org.postgresql:postgresql")
	liquibaseRuntime(sourceSets.getByName("main").output)
}
apply(plugin = "org.liquibase.gradle")

liquibase {
	activities.register("main") {
		this.arguments = mapOf(
			"logLevel" to "info",
			"changeLogFile" to "src/main/resources/migrations/changelog.sql",
			"url" to "jdbc:postgresql://localhost:5432/postgres",
			"username" to "postgres",
			"password" to "postgres"
		)
	}
	runList = "main"
}

buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.liquibase:liquibase-gradle-plugin:2.2.0")
	}
}
apply(plugin = "org.liquibase.gradle")

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
