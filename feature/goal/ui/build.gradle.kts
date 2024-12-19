plugins {
    alias(libs.plugins.levelist.android.library)
    alias(libs.plugins.levelist.android.lib.compose)
}

android {
    namespace = "com.mr.levelist.feature.goal.ui"
}

dependencies {
    implementation(projects.core.ui)
}