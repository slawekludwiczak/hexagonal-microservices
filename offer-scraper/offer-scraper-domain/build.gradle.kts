apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

plugins {
    id("java")
}

dependencies {
    implementation(libs.jsoup)
    testImplementation(libs.junit.jupiter)
}

tasks.test {
    useJUnitPlatform()
}