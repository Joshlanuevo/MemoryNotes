// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("coroutines_version", "1.6.4")
        set("room_version", "2.5.2")
        set("glide_version", "4.15.1")
        set("nav_version", "2.7.3")
        set("daggerVersion", "2.46.1")
    }

    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${project.extra["nav_version"]}")
    }
}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    kotlin("android") version "1.9.0" apply false
}