pluginManagement {
    val kotlinPluginVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinPluginVersion
        kotlin("plugin.spring") version kotlinPluginVersion
        kotlin("plugin.allopen") version kotlinPluginVersion
        kotlin("plugin.noarg") version kotlinPluginVersion
        kotlin("kapt") version kotlinPluginVersion
    }
}

rootProject.name = "dev"
include("dev-core")
include("dev-admin-api")
include("dev-home-api")
