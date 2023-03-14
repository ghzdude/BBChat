@file:Suppress("PropertyName")

val mod_version: String by project

allprojects {
    group = "com.breakinblocks.bbchat"
    version = mod_version
}

subprojects {
    repositories {
        mavenCentral {
            content {
                includeGroup("com.fasterxml")
                includeGroup("com.fasterxml.jackson")
                includeGroup("com.fasterxml.jackson.core")
                includeGroup("com.google.code.findbugs")
                includeGroup("com.google.guava")
                includeGroup("com.neovisionaries")
                includeGroup("com.squareup.okhttp3")
                includeGroup("com.squareup.okio")
                includeGroup("net.dv8tion")
                includeGroup("net.sf.trove4j")
                includeGroup("org.apache")
                includeGroup("org.ajoberstar.grgit")
                includeGroup("org.apache.commons")
                includeGroup("org.apache.logging")
                includeGroup("org.apache.logging.log4j")
                includeGroup("org.jetbrains")
                includeGroup("org.jetbrains.kotlin")
                includeGroup("org.slf4j")
                includeGroup("org.sonatype.oss")
            }
        }
    }
}

plugins {
    // idk wtf im doing
    id("org.jetbrains.gradle.plugin.idea-ext").version("1.1.7")
    id("org.ajoberstar.grgit").version("4.1.1")
    id("com.palantir.git-version").version("0.13.0")
    id("com.diffplug.spotless").version("6.7.2")
    id("com.modrinth.minotaur").version("2.+")
    // id 'com.modrinth.minotaur' version '2.+' apply false
    id("com.matthewprenger.cursegradle").version("1.4.0")
    // id 'com.matthewprenger.cursegradle' version '1.4.0' apply false
    id("com.gtnewhorizons.retrofuturagradle").version("1.2.3")
    // id 'com.gtnewhorizons.retrofuturagradle' version '1.2.3'
}
