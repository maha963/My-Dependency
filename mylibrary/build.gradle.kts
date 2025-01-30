plugins {
    alias(libs.plugins.android.library)
    id ("maven-publish")
}

android {
    namespace = "com.example.mylibrary"
    compileSdk = 34

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}


afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("ReleaseAar") {
                groupId = "com.github.maha963" // Use your GitHub username or appropriate group ID
                artifactId = "mylibrary" // The name of your library
                version = "1.0.0" // Version of the library

                // Link the generated AAR file to the MavenPublication
                artifact(tasks.getByName("bundleReleaseAar"))
            }
        }
    }
}

