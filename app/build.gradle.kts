plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
}

android {
    namespace = "ru.nak.ied.roomtest"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.nak.ied.roomtest"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val room_version = "2.6.1"

    implementation(libs.androidx.core.ktx)

    implementation("androidx.room:room-ktx:$room_version")
    val lifecycle_version = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    ksp("androidx.room:room-compiler:$room_version")
//    implementation("androidx.room:room-ktx:2.4.3")
//    implementation("androidx.room:room-compiler:2.4.3")
//    runtimeOnly("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}