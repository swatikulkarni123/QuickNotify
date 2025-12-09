//import java.util.Properties
//
//// Load GitHub credentials from a local file (ignored by git)
//val githubProperties = Properties().apply {
//    load(rootProject.file("github.properties").inputStream())
//}

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("maven-publish")
}

group = "com.swa.quicknotify"
version = "1.0.1"

android {
    namespace = "com.swa.quicknotify"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

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
    kotlinOptions {
        jvmTarget = "11"
    }
}
dependencies {
    implementation(platform(libs.androidx.compose.bom)) // BOM
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3) // BOM-managed version
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.lifecycle.process)
    implementation("androidx.startup:startup-runtime:1.2.0")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.swa.quicknotify"
            artifactId = "quicknotify"
            version = "1.0.1"

            afterEvaluate {
                from(components["release"])
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/swatikulkarni123/QuickNotify")
//            credentials {
//                username = githubProperties["gpr.user"] as String
//                password = githubProperties["gpr.token"] as String
//            }
            //println("Loaded GitHub User: " + githubProperties["gpr.user"])
        }
    }
}