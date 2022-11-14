plugins {
    idea
    kotlin("jvm") version "1.7.20"
    id("io.papermc.paperweight.userdev") version "1.3.8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

group = "com.skillx"


idea {
    module {
        excludeDirs.add(file(".server"))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    paperDevBundle("1.19.2-R0.1-SNAPSHOT")
    testImplementation(kotlin("test"))
    implementation("io.github.monun:kommand-api:2.14.0")
}

extra.apply {
    set("pluginName", project.name.split('-').joinToString("") { it.capitalize() })
    set("packageName", project.name.replace("-", ""))
    set("kotlinVersion", "1.7.20")
    set("paperVersion", "1.19.2")
}

tasks {
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    jar {
        archiveFileName.set(project.name + "-dev.jar")
        destinationDirectory.set(file(".server/plugins"))
    }

    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    test {
        useJUnitPlatform()
    }

//    reobfJar {
//        outputJar.set(file(".server/plugins/${project.name}-${project.version}-reobf.jar"))
//    }

}
