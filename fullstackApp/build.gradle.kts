import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kvision)
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "main.bundle.js"
                sourceMaps = false
            }
            runTask {
                sourceMaps = false
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    jvm {
        group = "com.just.tracker"
        version = "1.0.0"

        withJava()
        compilerOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }

        mainRun {
            mainClass.set("com.just.tracker.ServerApplicationKt")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kvision.server.ktor.koin)
                implementation(projects.shared)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test.common)
                implementation(libs.kotlin.test.annotations.common)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(libs.kvision)
                implementation(libs.kvision.fontawesome)
                implementation(libs.kvision.bootstrap)
                implementation(libs.kvision.material)
                implementation(libs.kvision.i18n)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(libs.kotlin.test.js)
                implementation(libs.kvision.testutils)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(libs.ktor.server.netty)
                implementation(libs.ktor.server.auth)
                implementation(libs.ktor.server.compression)
                implementation(libs.ktor.server.yaml)
                implementation(libs.koin.annotations)

                implementation(libs.logback)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.ktor.server.tests)
                implementation(libs.kotlin.test.junit)
            }
        }
    }
}