import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

val secretPropsFile = File("secret.properties")
val secretProps = Properties()
if (secretPropsFile.exists()) {
    secretProps.load(secretPropsFile.inputStream())
}

android {
    namespace = "com.example.githubusersdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "API_KEY", "\"${secretProps["API_KEY"]}\"")
    }

    buildFeatures {
        buildConfig = true
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
    publishing {
        publishing {
            singleVariant("release") {
                withSourcesJar()
                withJavadocJar()
            }
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.s2g090123"
            artifactId = "GitHubUserSDK"
            version = "v1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}