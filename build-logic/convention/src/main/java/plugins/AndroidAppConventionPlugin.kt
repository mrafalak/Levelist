package plugins

import com.android.build.api.dsl.ApplicationExtension
import config.Config
import extensions.configureAndroidKotlin
import extensions.configureAppBuildTypes
import extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.util.Properties

class AndroidAppConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
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
            }

            extensions.configure<ApplicationExtension> {
                val keystoreFile = project.rootProject.file("local.properties")
                val properties = Properties()
                properties.load(keystoreFile.inputStream())

                signingConfigs {
                    create("release") {
                        storeFile = file(properties["KEYSTORE_PATH"] as String)
                        storePassword = properties["KEYSTORE_PASSWORD"] as String
                        keyAlias = properties["KEY_ALIAS"] as String
                        keyPassword = properties["KEY_PASSWORD"] as String
                    }
                }

                configureAndroidKotlin(this)
                configureAppBuildTypes(this)

                defaultConfig {
                    namespace = Config.android.namespace
                    applicationId = Config.android.applicationId
                    targetSdk = Config.android.targetSdkVersion
                    versionCode = Config.android.versionCode
                    versionName = Config.android.versionName

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }

                buildFeatures.buildConfig = true

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
        }
    }
}