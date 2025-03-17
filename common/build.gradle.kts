plugins {
    alias(libs.plugins.maven.deployer)
}

dependencies {
    compileOnlyApi(libs.java.semver) // Since java semver should be shaded by the dependent project'
    implementation(libs.json)

    testImplementation(libs.java.semver)
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
    relocate("org.json", "io.github.milkdrinkers.versionwatch.lib.json")
    minimize()
}

deployer {
    release {
        version.set("${rootProject.version}")
        description.set(rootProject.description.orEmpty())
    }

    projectInfo {
        groupId = "io.github.milkdrinkers"
        artifactId = "versionwatch"
        version = "${rootProject.version}"

        name = rootProject.name
        description = rootProject.description.orEmpty()
        url = "https://github.com/milkdrinkers/versionwatch"

        scm {
            connection = "scm:git:git://github.com/milkdrinkers/versionwatch.git"
            developerConnection = "scm:git:ssh://github.com:milkdrinkers/versionwatch.git"
            url = "https://github.com/milkdrinkers/versionwatch"
        }

        license("MIT License", "https://opensource.org/licenses/MIT")

        developer({
            name.set("darksaid98")
            email.set("darksaid9889@gmail.com")
            url.set("https://github.com/darksaid98")
            organization.set("Milkdrinkers")
        })
    }

    content {
        component {
            fromJava()
        }
    }

    centralPortalSpec {
        auth.user.set(secret("MAVEN_USERNAME"))
        auth.password.set(secret("MAVEN_PASSWORD"))
    }

    signing {
        key.set(secret("GPG_KEY"))
        password.set(secret("GPG_PASSWORD"))
    }
}