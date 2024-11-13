plugins {
    alias(libs.plugins.levelist.android.application)
    alias(libs.plugins.levelist.android.app.compose)
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
}