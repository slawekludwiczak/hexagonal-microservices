apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

plugins {
    id("java")
}

dependencies {
    implementation(project(":product-catalog-domain"))
    testImplementation(libs.junit.jupiter)
}

tasks.test {
    useJUnitPlatform()
}