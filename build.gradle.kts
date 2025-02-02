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
