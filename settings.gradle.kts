rootProject.name = "minestom-data"
// DataGenerator
include("DataGenerator")

pluginManagement {
    repositories {
        maven(url = "https://repo.spongepowered.org/repository/maven-public/")
        gradlePluginPortal()
    }
}

plugins {
    id("org.spongepowered.gradle.vanilla") version "0.2.1-SNAPSHOT"
    // ... anything else ...
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    // repositories declared¥
}

rootProject.name = "minestom-data"
// DataGenerator
include("DataGenerator")
