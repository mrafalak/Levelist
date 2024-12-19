plugins {
    alias(libs.plugins.levelist.android.library)
}

android {
    namespace = "com.mr.levelist.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
}