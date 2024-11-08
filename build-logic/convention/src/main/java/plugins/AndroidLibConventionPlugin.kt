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
            }

            dependencies {
                "implementation"(platform(versionCatalog().findLibrary("koin-bom").get()))
                "implementation"(versionCatalog().findLibrary("koin-core").get())
                "implementation"(versionCatalog().findLibrary("androidx-core-ktx").get())
                "implementation"(versionCatalog().findLibrary("timber-core").get())
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