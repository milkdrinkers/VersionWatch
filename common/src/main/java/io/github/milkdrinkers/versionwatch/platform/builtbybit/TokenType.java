package io.github.milkdrinkers.versionwatch.platform.builtbybit;

@SuppressWarnings("unused")
public enum TokenType {
    SHARED("Shared"),
    @SuppressWarnings("unused") PRIVATE("Private");

    private final String tokenTypeName;

    TokenType(final String tokenTypeName) {
        this.tokenTypeName = tokenTypeName;
    }

    public String getTokenTypeName() {
        return tokenTypeName;
    }
}
