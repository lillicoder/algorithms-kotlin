dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(project(":collections"))
    implementation(project(":trees"))
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}
