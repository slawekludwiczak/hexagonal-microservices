apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

plugins {
    id("java")
}

dependencies {
    implementation(libs.jsoup)
    implementation(libs.slf4j)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.bundles.mockito)
}

tasks.test {
    useJUnitPlatform()
}