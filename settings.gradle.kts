plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kupdate"

buildscript {
    repositories {
        maven("https://maven-central-asia.storage-download.googleapis.com/maven2/")
    }
}