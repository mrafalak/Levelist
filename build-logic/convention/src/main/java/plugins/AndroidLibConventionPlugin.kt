package plugins

import com.android.build.gradle.LibraryExtension
import config.Config
import extensions.configureAndroidKotlin
import extensions.configureLibraryBuildTypes
import extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                "implementation"(platform(versionCatalog().findLibrary("koin-bom").get()))
                "implementation"(versionCatalog().findLibrary("koin-core").get())
                "implementation"(versionCatalog().findLibrary("koin-android").get())
                "implementation"(versionCatalog().findLibrary("androidx-core-ktx").get())
                "implementation"(versionCatalog().findLibrary("timber-core").get())
                "implementation"(versionCatalog().findLibrary("kotlinx-serialization").get())
                "implementation"(
                    platform(
                        versionCatalog().findLibrary("google-firebase-bom").get()
                    )
                )
                "implementation"(versionCatalog().findLibrary("google-firebase-analytics").get())
                "implementation"(versionCatalog().findLibrary("google-firebase-config").get())
            }

            extensions.configure<LibraryExtension> {
                configureAndroidKotlin(this)
                configureLibraryBuildTypes(this)

                defaultConfig.targetSdk = Config.android.targetSdkVersion

                buildFeatures.buildConfig = true
            }
        }
    }
}