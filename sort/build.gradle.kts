dependencies {
    implementation(project(":collections"))
    implementation(project(":heaps"))
    implementation(project(":trees"))
    implementation(libs.kotlin.stdlib)
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}
