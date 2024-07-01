apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

plugins {
    id("java")
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation(project(":offer-scraper-domain"))
    implementation (libs.spring.boot.starter.web)
    implementation (libs.spring.boot.starter.actuator)
    implementation (libs.spring.boot.devtools)
    implementation (libs.spring.boot.starter.oauth2.resource.server)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

