apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

plugins {
    id("java")
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management)
}

extra["springCloudVersion"] = libs.versions.spring.cloud

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

dependencies {
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
    implementation (libs.spring.boot.starter.web)
}

