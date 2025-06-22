import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //alias(libs.plugins.kotlin.compose)

    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.8.21"
}

// Read from local.properties
val localProperties = File(rootProject.rootDir, "local.properties")
    .reader().use {
        Properties().apply { load(it) }
    }

val apiKey = localProperties.getProperty("API_KEY", "")
val paymentApiKey = localProperties.getProperty("PAYMENT_API_KEY", "")

android {
    namespace = "com.zepto"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.zepto"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        compose = true
    }
    // Add this configuration to resolve duplicate class issues
    configurations.all {
        resolutionStrategy {
            // Force a specific version for the gotrue-kt-android library
            force("io.github.jan-tennert.supabase:gotrue-kt-android:1.3.0")

            // Exclude the debug variant that's causing duplicates
            exclude(group = "io.github.jan-tennert.supabase", module = "gotrue-kt-android-debug")
        }
    }
}
kotlin {
    jvmToolchain(17)
}

dependencies {

    /*implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")*/

    implementation("androidx.compose.runtime:runtime")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.play.services.auth)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.appcheck.ktx)
    implementation(libs.volley)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

// Media3 ExoPlayer dependencies
    implementation("androidx.media3:media3-exoplayer:1.2.1")
    implementation("androidx.media3:media3-ui:1.2.1")
    implementation("androidx.compose.ui:ui-text-google-fonts")
    implementation(libs.androidx.constraintlayout.compose)

// Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

// Coil for image loading
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("io.coil-kt:coil-gif:2.5.0")

    // Navigation Compose - the main navigation library
    implementation("androidx.navigation:navigation-compose:2.7.7")

// Dagger Hilt core dependencies
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

// Hilt for Jetpack components
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

// Lifecycle components with Hilt integration
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

// Retrofit for API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Location services
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.accompanist:accompanist-permissions:0.30.1")

// Supabase - fixed dependencies to avoid conflicts
    implementation("io.github.jan-tennert.supabase:supabase-kt:1.3.0")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:1.3.0") {
        exclude(group = "io.github.jan-tennert.supabase", module = "gotrue-kt-android-debug")
    }
    implementation("io.github.jan-tennert.supabase:gotrue-kt-android:1.3.0") {
        exclude(group = "io.github.jan-tennert.supabase", module = "gotrue-kt-debug")
    }

// Skydoves
    implementation("com.github.skydoves:cloudy:0.1.2")
// Required Ktor dependencies
    implementation("io.ktor:ktor-client-core:2.3.3")
    implementation("io.ktor:ktor-client-cio:2.3.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3")

// Razorpay
    implementation("com.razorpay:checkout:1.6.33")

// Required serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

// Firebase App Check SafetyNet
    implementation("com.google.firebase:firebase-appcheck-safetynet:16.1.2")

// Logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

// Typography
    implementation("androidx.compose.ui:ui-text-google-fonts:1.5.4")


}