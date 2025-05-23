pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0" // allow automatic download of JDKs
}

rootProject.name = "VersionWatch"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "common",
)