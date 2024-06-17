plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

dependencies {
    implementation(project(":product-catalog-domain"))
    implementation (libs.spring.boot.starter.web)
    testImplementation (libs.spring.boot.starter.test)
}

tasks.test {
    useJUnitPlatform()
}