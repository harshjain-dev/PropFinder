pluginManagement {
    includeBuild("build-logic")
    repositories {
        google ()
        mavenCentral()
        gradlePluginPortal()
    }
}
//plugins {
//    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
//}
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("./build-logic/gradle/libs.versions.toml"))
        }
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PropFinder"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":data")
include(":domain")
