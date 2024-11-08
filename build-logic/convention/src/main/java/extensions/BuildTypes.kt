package extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

internal fun Project.configureAppBuildTypes(
    appExt: ApplicationExtension,
) {
    appExt.apply {
        buildTypes {
            getByName("release") {
                signingConfig = signingConfigs.getByName("release")
                isMinifyEnabled = false
                isShrinkResources = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    file("proguard-rules.pro")
                )
            }
            getByName("debug") {
                isMinifyEnabled = false
                isShrinkResources = false
            }
        }
    }
}

internal fun Project.configureLibraryBuildTypes(
    libExt: LibraryExtension,
) {
    libExt.apply {
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    file("proguard-rules.pro")
                )
            }
            getByName("debug") {
                isMinifyEnabled = false
            }
        }
    }
}