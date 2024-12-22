package io.github.milkdrinkers.versionwatch.platform.builtbybit;

public enum TokenType {
    SHARED("Shared"),
    PRIVATE("Private");

    private final String tokenTypeName;

    TokenType(final String tokenTypeName) {
        this.tokenTypeName = tokenTypeName;
    }

    public String getTokenTypeName() {
        return tokenTypeName;
    }
}
