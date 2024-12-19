plugins {
    alias(libs.plugins.levelist.android.library)
    alias(libs.plugins.levelist.android.lib.compose)
}

android {
    namespace = "com.mr.levelist.core.ui"
}

dependencies {
    implementation(projects.core.domain)
}