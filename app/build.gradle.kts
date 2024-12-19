plugins {
    alias(libs.plugins.levelist.android.application)
    alias(libs.plugins.levelist.android.app.compose)
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.ui)

    implementation(projects.feature.goal.ui)
    implementation(projects.feature.habit.ui)
    implementation(projects.feature.settings.ui)
    implementation(projects.feature.welcome.ui)
}