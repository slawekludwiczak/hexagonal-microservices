plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

dependencies {
    implementation(project(":product-catalog-domain"))
    implementation (libs.spring.boot.starter.web)
    implementation (libs.spring.boot.starter.oauth2.resource.server)
    testImplementation (libs.spring.boot.starter.test)
    testImplementation (libs.spring.security.test)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.test {
    useJUnitPlatform()
}