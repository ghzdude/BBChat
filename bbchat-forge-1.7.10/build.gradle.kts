buildscript {
    repositories {
        mavenCentral()
        maven { url = 'https://files.minecraftforge.net/maven' }
    }
    dependencies {
        classpath('com.anatawa12.forge:ForgeGradle:1.2-1.0.+') {
            changing = true
        }
    }
}

apply plugin: 'forge'
apply plugin: 'com.github.johnrengelman.shadow'
def fg_plugin = plugins.findPlugin 'forge'

archivesBaseName = "bbchat-${mc_version}"

minecraft {
    version = "${mc_version}-${forge_version}-${mc_version}"
    runDir = 'run'

    replace 'version = ""', "version = \"${mod_version}\""
    replace 'dependencies = ""', "dependencies = \"required-after:Forge@${forge_version_range_supported};\""
    replace 'acceptedMinecraftVersions = ""', "acceptedMinecraftVersions = \"${mc_version_range_supported}\""
    replaceIn "BBChat.java"
}

dependencies {
    compile project(path: ':bbchat-common', configuration: 'shadow')
}

processResources {
    // this will ensure that this task is redone when the versions change.
    inputs.property 'mod_version', project.mod_version
    inputs.property 'mc_version', project.mc_version

    // replace stuff in mcmod.info, nothing else
    from(sourceSets.main.resources.srcDirs) {
        include 'mcmod.info'

        // replace mod_version and mc_version_range_supported and forge_version_major
        expand 'mod_version': "${mod_version}",
                'mc_version': "${mc_version}"
    }

    // copy everything else except the mcmod.info
    from(sourceSets.main.resources.srcDirs) {
        exclude 'mcmod.info'
    }
}

jar {
    manifest {
        attributes 'FMLAT': 'bbchat_at.cfg'
    }
}

shadowJar {
    classifier = 'forge'
    dependencies {
        include(project(':bbchat-common'))
    }
    exclude 'dummyThing'
    exclude '.cache'
    exclude 'GradleStart*.class'
    exclude 'net/minecraftforge/gradle/*'
}

// Source: https://github.com/Team-Fruit/BnnWidget/blob/32736398f19f7ae89874c47535f8816f02b6c2db/build.subprojects.gradle#L284-L305
// License: MIT @ https://github.com/Team-Fruit/BnnWidget/blob/32736398f19f7ae89874c47535f8816f02b6c2db/LICENSE
task reobfShadowJar(dependsOn: 'genSrgs', type: net.minecraftforge.gradle.tasks.user.reobf.ReobfTask) {
    exceptorCfg = fg_plugin.delayedFile net.minecraftforge.gradle.user.UserConstants.EXC_SRG
    srg = fg_plugin.delayedFile net.minecraftforge.gradle.user.UserConstants.REOBF_SRG
    fieldCsv = fg_plugin.delayedFile net.minecraftforge.gradle.user.UserConstants.FIELD_CSV
    fieldCsv = fg_plugin.delayedFile net.minecraftforge.gradle.user.UserConstants.METHOD_CSV
    mcVersion = fg_plugin.delayedString '{MC_VERSION}'
    mustRunAfter 'test'
    mustRunAfter 'shadowJar'
    reobf.dependsOn 'reobfShadowJar'
    reobf(tasks.shadowJar) { arg ->
        def javaConv = project.convention.plugins.get 'java'
        arg.classpath = javaConv.getSourceSets().getByName('main').compileClasspath
    }
    extraSrg = fg_plugin.extension.srgExtra
    afterEvaluate {
        if (fg_plugin.extension.decomp) {
            deobfFile = tasks.deobfuscateJar.delayedOutput
            recompFile = fg_plugin.delayedDirtyFile fg_plugin.srcDepName, null, 'jar'
        }
    }
}
reobf.dependsOn 'reobfShadowJar'
