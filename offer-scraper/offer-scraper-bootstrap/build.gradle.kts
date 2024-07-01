plugins {
    id("java")
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation(project(":offer-scraper-persistence"))
    implementation(project(":offer-scraper-scheduler"))
    implementation(project(":offer-scraper-api"))
    implementation(project(":offer-scraper-domain"))
    implementation (libs.spring.boot.starter)
}

group = "com.ludigi"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}
