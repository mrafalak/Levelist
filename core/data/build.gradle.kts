plugins {
    alias(libs.plugins.levelist.android.library)
}

android {
    namespace = "com.mr.levelist.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
}