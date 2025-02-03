package extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.gradle.LibraryExtension
import config.BuildTypeEnum
import config.Config.DEFAULT_PROGUARD_FILE
import config.Config.PROGUARD_RULES_FILE
import config.Config.buildConfigFlags
import org.gradle.api.Project

internal fun Project.configureAppBuildTypes(
    appExt: ApplicationExtension,
) {
    appExt.apply {
        buildTypes {
            getByName(BuildTypeEnum.RELEASE.value) {
                signingConfig = signingConfigs.getByName(BuildTypeEnum.RELEASE.value)
                isMinifyEnabled = false
                isShrinkResources = false
                proguardFiles(
                    getDefaultProguardFile(DEFAULT_PROGUARD_FILE),
                    file(PROGUARD_RULES_FILE)
                )
                addBuildConfigFlags(BuildTypeEnum.RELEASE)
            }
            getByName(BuildTypeEnum.DEBUG.value) {
                isMinifyEnabled = false
                isShrinkResources = false
                addBuildConfigFlags(BuildTypeEnum.DEBUG)
            }
        }
    }
}

internal fun Project.configureLibraryBuildTypes(
    libExt: LibraryExtension,
) {
    libExt.apply {
        buildTypes {
            getByName(BuildTypeEnum.RELEASE.value) {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile(DEFAULT_PROGUARD_FILE),
                    file(PROGUARD_RULES_FILE)
                )
                addBuildConfigFlags(BuildTypeEnum.RELEASE)
            }
            getByName(BuildTypeEnum.DEBUG.value) {
                isMinifyEnabled = false
                addBuildConfigFlags(BuildTypeEnum.DEBUG)
            }
        }
    }
}

private fun BuildType.addBuildConfigFlags(buildType: BuildTypeEnum) {
    buildConfigFlags.forEach { (flagName, flagValues) ->
        val value = flagValues[buildType]
        if (value != null) {
            buildConfigField("boolean", flagName, value)
        }
    }
}