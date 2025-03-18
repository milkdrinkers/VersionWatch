import io.github.milkdrinkers.javasemver.Version;
import io.github.milkdrinkers.versionwatch.Platform;
import io.github.milkdrinkers.versionwatch.VersionWatcher;
import io.github.milkdrinkers.versionwatch.platform.builtbybit.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTest {
    final Version currentVersion = Version.of("0.0.1");

    @Test
    public void builtbybit() {
        final VersionWatcher watcher = VersionWatcher.builder()
            .withPlatform(Platform.BuiltByBit)
            .withVersion(currentVersion)
            .withResourceId("55363")
            .withToken("")
            .withTokenType(TokenType.SHARED)
            .withAgent("VersionWatch Test")
            .build();

        watcher.fetchLatest();

        System.out.println(watcher.getLatestVersion());
        System.out.println(watcher.getDownloadURL());
        Assertions.assertNull(watcher.getLatestVersion());
    }

    @Test
    public void curseforge() {
        final VersionWatcher watcher = VersionWatcher.builder()
            .withPlatform(Platform.CurseForge)
            .withVersion(currentVersion)
            .withResourceId("34767")
            .withResourceSlug("grief-prevention")
            .withApiKey("")
            .withAgent("VersionWatch Test")
            .build();

        watcher.fetchLatest();

        System.out.println(watcher.getLatestVersion());
        System.out.println(watcher.getDownloadURL());
        Assertions.assertNull(watcher.getLatestVersion());
    }

    @Test
    public void github() {
        final VersionWatcher watcher = VersionWatcher.builder()
            .withPlatform(Platform.GitHub)
            .withVersion(currentVersion)
            .withResourceSlug("Enderchester")
            .withResourceOwner("milkdrinkers")
            .withAgent("VersionWatch Test")
            .build();

        watcher.fetchLatest();
        Assertions.assertNotNull(watcher.getLatestVersion());

        System.out.println(watcher.getLatestVersion().getVersionFull());
        System.out.println(watcher.getDownloadURL());
        Assertions.assertFalse(watcher.isLatest());
    }

    @Test
    public void hangarId() {
        final VersionWatcher watcher = VersionWatcher.builder()
            .withPlatform(Platform.Hangar)
            .withVersion(currentVersion)
            .withResourceId("3110")
            .withResourceOwner("darksaid98")
            .withAgent("VersionWatch Test")
            .build();

        watcher.fetchLatest();
        Assertions.assertNotNull(watcher.getLatestVersion());

        System.out.println(watcher.getLatestVersion().getVersionFull());
        System.out.println(watcher.getDownloadURL());
        Assertions.assertFalse(watcher.isLatest());
        Assertions.assertTrue(watcher.isOutdated());
    }

    @Test
    public void hangarSlug() {
        final VersionWatcher watcher = VersionWatcher.builder()
            .withPlatform(Platform.Hangar)
            .withVersion(currentVersion)
            .withResourceSlug("Enderchester")
            .withResourceOwner("darksaid98")
            .withAgent("VersionWatch Test")
            .build();

        watcher.fetchLatest();
        Assertions.assertNotNull(watcher.getLatestVersion());

        System.out.println(watcher.getLatestVersion().getVersionFull());
        System.out.println(watcher.getDownloadURL());
        Assertions.assertFalse(watcher.isLatest());
    }

    @Test
    public void modrinthId() {
        final VersionWatcher watcher = VersionWatcher.builder()
            .withPlatform(Platform.Modrinth)
            .withVersion(currentVersion)
            .withResourceId("iEpIvtaS")
            .withAgent("VersionWatch Test")
            .build();

        watcher.fetchLatest();
        Assertions.assertNotNull(watcher.getLatestVersion());

        System.out.println(watcher.getLatestVersion().getVersionFull());
        System.out.println(watcher.getDownloadURL());
        Assertions.assertFalse(watcher.isLatest());
    }

    @Test
    public void modrinthSlug() {
        final VersionWatcher watcher = VersionWatcher.builder()
            .withPlatform(Platform.Modrinth)
            .withVersion(currentVersion)
            .withResourceSlug("enderchester")
            .withAgent("VersionWatch Test")
            .build();

        watcher.fetchLatest();
        Assertions.assertNotNull(watcher.getLatestVersion());

        System.out.println(watcher.getLatestVersion().getVersionFull());
        System.out.println(watcher.getDownloadURL());
        Assertions.assertFalse(watcher.isLatest());
    }

    @Test
    public void polymart() {
        final VersionWatcher watcher = VersionWatcher.builder()
            .withPlatform(Platform.Polymart)
            .withVersion(currentVersion)
            .withResourceId("773")
            .withAgent("VersionWatch Test")
            .build();

        watcher.fetchLatest();
        Assertions.assertNotNull(watcher.getLatestVersion());

        System.out.println(watcher.getLatestVersion().getVersionFull());
        System.out.println(watcher.getDownloadURL());
        Assertions.assertFalse(watcher.isLatest());
    }

    @Test
    public void spigot() {
        final VersionWatcher watcher = VersionWatcher.builder()
            .withPlatform(Platform.Spigot)
            .withVersion(currentVersion)
            .withResourceId("121384")
            .withAgent("VersionWatch Test")
            .build();

        watcher.fetchLatest();
        Assertions.assertNotNull(watcher.getLatestVersion());

        System.out.println(watcher.getLatestVersion().getVersionFull());
        System.out.println(watcher.getDownloadURL());
        Assertions.assertFalse(watcher.isLatest());
    }
}
