import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	id("org.springframework.boot") version "2.7.2"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	id("java")
	id("org.springframework.experimental.aot") version "0.12.1"
	id("org.jetbrains.kotlin.jvm") version "1.7.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	maven { url = uri("https://repo.spring.io/release") }
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

ext {
	set("springCloudVersion", "2021.0.3")
	set("springShellVersion", "2.1.0-RC1")
}

dependencies {
	implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
	implementation ("org.springframework.boot:spring-boot-starter-actuator")
	implementation ("org.springframework.boot:spring-boot-starter-webflux")
	implementation ("org.springframework.shell:spring-shell-starter")
	compileOnly ("org.projectlombok:lombok")
	developmentOnly ("org.springframework.boot:spring-boot-devtools")
	runtimeOnly ("io.micrometer:micrometer-registry-prometheus")
	annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor ("org.projectlombok:lombok")
	testImplementation ("org.springframework.boot:spring-boot-starter-test")
	implementation ("org.jetbrains.kotlin:kotlin-reflect")
	implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

dependencyManagement {
	imports {
		mavenBom ("org.springframework.shell:spring-shell-dependencies:${property("springShellVersion")}")
		mavenBom ("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
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

tasks.withType<BootBuildImage> {
	builder = "paketobuildpacks/builder:tiny"
	environment = mapOf("BP_NATIVE_IMAGE" to "true")
}

