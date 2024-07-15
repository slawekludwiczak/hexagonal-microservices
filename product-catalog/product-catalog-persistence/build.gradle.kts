apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

plugins {
    id("java")
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation(project(":product-catalog-domain"))
    implementation(libs.spring.boot.starter.data.jpa)
    implementation (libs.spring.boot.devtools)
    implementation(libs.h2)
    testImplementation(libs.spring.boot.starter.test)
}

tasks.test {
    useJUnitPlatform()
}
