plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.robined.valtteriitsjames"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.robined.valtteriitsjames"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    val composeBom = platform("androidx.compose:compose-bom:2024.09.03")
    implementation(composeBom)
    androidTestImplementation(composeBom)

//    // Choose one of the following:
    // Material Design 3
    implementation(libs.androidx.material3)
//    // or Material Design 2
//    implementation("androidx.compose.material:material")
//    // or skip Material Design and build directly on top of foundational components
//    implementation("androidx.compose.foundation:foundation")
//    // or only import the main APIs for the underlying toolkit systems,
//    // such as input and measurement/layout
    implementation(libs.androidx.ui)

    // Android Studio Preview support
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

//    // UI Tests
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")

//    // Optional - Included automatically by material, only add when you need
//    // the icons but not the material library (e.g. when using Material3 or a
//    // custom design system based on Foundation)
//    implementation("androidx.compose.material:material-icons-core")
//    // Optional - Add full set of material icons
//    implementation("androidx.compose.material:material-icons-extended")
//    // Optional - Add window size utils
//    implementation("androidx.compose.material3.adaptive:adaptive")

    // Optional - Integration with activities
    implementation(libs.androidx.activity.compose)
//    // Optional - Integration with ViewModels
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
//    // Optional - Integration with LiveData
//    implementation("androidx.compose.runtime:runtime-livedata")
//    // Optional - Integration with RxJava
//    implementation("androidx.compose.runtime:runtime-rxjava2")
}