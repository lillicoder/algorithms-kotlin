plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ktlint.gradle) apply false
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "application")

    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
        jvmToolchain(21)
    }
}
