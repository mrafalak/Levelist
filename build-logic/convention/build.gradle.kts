import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApp") {
            id = libs.plugins.levelist.android.application.get().pluginId
            implementationClass = "plugins.AndroidAppConventionPlugin"
        }
        register("androidAppCompose") {
            id = libs.plugins.levelist.android.app.compose.get().pluginId
            implementationClass = "plugins.AndroidAppComposeConventionPlugin"
        }
        register("androidLib") {
            id = libs.plugins.levelist.android.library.get().pluginId
            implementationClass = "plugins.AndroidLibConventionPlugin"
        }
        register("androidLibCompose") {
            id = libs.plugins.levelist.android.lib.compose.get().pluginId
            implementationClass = "plugins.AndroidLibComposeConventionPlugin"
        }
    }
}