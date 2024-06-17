apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    implementation(project(":product-catalog-domain"))
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.h2)
    testImplementation(libs.spring.boot.starter.test)
}

tasks.test {
    useJUnitPlatform()
}