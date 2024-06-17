plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

dependencies {
    implementation(project(":product-catalog-domain"))
    implementation(project(":product-catalog-api"))
    implementation(project(":product-catalog-persistence"))
    implementation (libs.spring.boot.starter)
    testImplementation (libs.spring.boot.starter.test)
}

tasks.test {
    useJUnitPlatform()
}