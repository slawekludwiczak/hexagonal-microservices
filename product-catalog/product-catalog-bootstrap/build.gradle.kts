plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

extra["springCloudVersion"] = "2023.0.2"

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

dependencies {
    implementation(project(":product-catalog-domain"))
    implementation(project(":product-catalog-api"))
    implementation(project(":product-catalog-persistence"))
    implementation(project(":product-catalog-consul"))
    implementation (libs.spring.boot.starter)
    testImplementation (libs.spring.boot.starter.test)
    testRuntimeOnly(libs.junit.platform.launcher)
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
}

tasks.test {
    useJUnitPlatform()
}
