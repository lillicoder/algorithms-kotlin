dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(project(":collections"))
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}
