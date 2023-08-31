plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
apply("${project.rootDir}/tools/jacoco-project.gradle")
apply("${project.rootDir}/tools/jacoco.modules.gradle")

android {
    namespace = "com.fioalpha.myheroes"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.fioalpha.myheroes"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {

        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions += "version"

    productFlavors {
        create("product") {
            dimension = "version"
        }
        create("e2e") {
            dimension = "version"
            applicationIdSuffix = ".e2e"
            versionNameSuffix = ".e2e"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        buildConfig = true
    }
}


dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.constraint.layout)

    implementation(libs.koin.android)
    implementation(libs.koin.core)
    androidTestImplementation(libs.androidx.ui.test.junit4.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.compose.ui.test.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(project(":feature:character:presentation"))
}
