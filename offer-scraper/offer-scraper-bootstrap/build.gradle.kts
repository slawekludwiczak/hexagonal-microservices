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
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation(project(":offer-scraper-consul"))
    implementation(project(":offer-scraper-persistence"))
    implementation(project(":offer-scraper-scheduler"))
    implementation(project(":offer-scraper-events"))
    implementation(project(":offer-scraper-api"))
    implementation(project(":offer-scraper-domain"))
    implementation (libs.spring.boot.starter)
}
