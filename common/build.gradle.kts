plugins {
    alias(libs.plugins.maven.deployer)
}

dependencies {
    api(libs.gson)
    api(libs.java.semver)
}

tasks.build {
    dependsOn(tasks.shadowJar)
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

        license({
            name = "GNU General Public License Version 3"
            url = "https://www.gnu.org/licenses/gpl-3.0.en.html#license-text"
        })

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