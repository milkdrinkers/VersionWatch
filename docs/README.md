<h1 style="text-align:center;">VersionWatch</h1>

<p style="text-align:center;">
    <a href="https://github.com/milkdrinkers/VersionWatch/blob/main/LICENSE">
        <img alt="GitHub License" src="https://img.shields.io/github/license/milkdrinkers/VersionWatch?style=for-the-badge&color=blue&labelColor=141417">
    </a>
    <a href="https://central.sonatype.com/artifact/io.github.milkdrinkers/versionwatch">
        <img alt="Maven Central Version" src="https://img.shields.io/maven-central/v/io.github.milkdrinkers/versionwatch?style=for-the-badge&labelColor=141417">
    </a>
    <a href="https://milkdrinkers.athyrium.eu/versionwatch">
        <img alt="Documentation" src="https://img.shields.io/badge/DOCUMENTATION-900C3F?style=for-the-badge&labelColor=141417">
    </a>
    <a href="https://javadoc.io/doc/io.github.milkdrinkers/versionwatch">
        <img alt="Javadoc" src="https://img.shields.io/badge/JAVADOC-8A2BE2?style=for-the-badge&labelColor=141417">
    </a>
    <img alt="GitHub Actions Workflow Status" src="https://img.shields.io/github/actions/workflow/status/milkdrinkers/VersionWatch/ci.yml?style=for-the-badge&labelColor=141417">
    <a href="https://github.com/milkdrinkers/VersionWatch/issues">
        <img alt="GitHub Issues" src="https://img.shields.io/github/issues/milkdrinkers/VersionWatch?style=for-the-badge&labelColor=141417">
    </a>
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/milkdrinkers/VersionWatch?style=for-the-badge&labelColor=141417">
</p>

**VersionWatch** is a lightweight, easy-to-use Java library that simplifies version monitoring across popular software distribution platforms.

It supports both synchronous and asynchronous requests and leverages **Semantic Versioning (SemVer)** for parsing and comparing versions via the [`Java-Semver`](https://github.com/MilkDrinkers/Java-Semver) library.

---

## 🌟 Features

- ✅ **Java 8+ compatible**: Works with older and modern Java environments.  
- ⚡ **Sync/Async support**: Choose between blocking or non-blocking requests.  
- 🧪 **Well-tested**: Robust JUnit test coverage ensures reliability.  
- 🛠 **Simple API**: Minimal setup and intuitive methods.  
- 🔍 **SemVer Support**: Built on [`Java-Semver`](https://github.com/MilkDrinkers/Java-Semver) for reliable parsing and comparison of semantic versions.  
- 🚀 **Light**: The library is built with bundle size in mind to keep your application small.
- 🧩 **Wide Support**: Supports fetching versions from **Spigot**, **Modrinth**, **CurseForge**, **GitHub**, **Hangar**, **BuiltByBit** and **Polymart**. 

## 📦 Installation

Add VersionWatch and its `Java-Semver` dependency to your project with Maven or Gradle:

<details>
<summary>Gradle Kotlin DSL</summary>

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.milkdrinkers:versionwatch:LATEST_VERSION")
    implementation("io.github.milkdrinkers:javasemver:LATEST_VERSION")
}
```
</details>

<details>
<summary>Maven</summary>

```xml
<project>
    <dependencies>
        <dependency>  
            <groupId>io.github.milkdrinkers</groupId>  
            <artifactId>versionwatch</artifactId>  
            <version>LATEST_VERSION</version>  
        </dependency>  
        <dependency>  
            <groupId>io.github.milkdrinkers</groupId>  
            <artifactId>javasemver</artifactId>  
            <version>LATEST_VERSION</version>  
        </dependency>  
    </dependencies>
</project>
```
</details>

## Usage Example 🚀
```java
import io.github.milkdrinkers.javasemver.Version;
import io.github.milkdrinkers.versionwatch.Platform;
import io.github.milkdrinkers.versionwatch.VersionWatcher;

final Version currentVersion = Version.of("1.0.0");

// Build a version watcher for spigot
final VersionWatcher watcher = VersionWatcher.builder()
    .withPlatform(Platform.Spigot)
    .withVersion(currentVersion)
    .withResourceId("1234567")
    .withAgent("VersionWatch")
    .build();

// Fetch the latest version with a blocking request
final Version latestVersion = watcher.fetchLatest(); // The result is cached in the watcher

System.out.println("Has update: " + !watcher.isLatest().toString())  
```

## 📚 Documentation 

- [Full Javadoc Documentation](https://javadoc.io/doc/io.github.milkdrinkers/versionwatch)
- [Documentation](https://milkdrinkers.athyrium.eu/versionwatch)
- [Maven Central](https://central.sonatype.com/artifact/io.github.milkdrinkers/versionwatch)

---

## 🔨 Building from Source 

```bash
git clone https://github.com/milkdrinkers/VersionWatch.git
cd versionwatch
./gradlew publishToMavenLocal
```

---

## 🔧 Contributing

Contributions are always welcome! Please make sure to read our [Contributor's Guide](CONTRIBUTING.md) for standards and our [Contributor License Agreement (CLA)](CONTRIBUTOR_LICENSE_AGREEMENT.md) before submitting any pull requests.

We also ask that you adhere to our [Contributor Code of Conduct](CODE_OF_CONDUCT.md) to ensure this community remains a place where all feel welcome to participate.

---

## 📝 Licensing

You can find the license the source code and all assets are under [here](../LICENSE). Additionally, contributors agree to the Contributor License Agreement \(*CLA*\) found [here](CONTRIBUTOR_LICENSE_AGREEMENT.md).
