// Top-level build file where you can add configuration options common to all sub-projects/modules.
extra["compose_version"] = "1.3.0"
extra["koin_version"] = "3.3.0"
extra["coil_version"] = "2.2.2"
extra["nav_version"] = "2.5.3"
extra["paging_version"] = "3.2.1"

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.2.2" apply false
}