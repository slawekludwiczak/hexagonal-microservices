apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

plugins {
    id("java")
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.bundles.mockito)
}

tasks.test {
    useJUnitPlatform()
}