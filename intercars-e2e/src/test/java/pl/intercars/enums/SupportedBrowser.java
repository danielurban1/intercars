package pl.intercars.enums;

import java.util.Arrays;

public enum SupportedBrowser {
    CHROME,
    FIREFOX;

    public static SupportedBrowser getSupportedBrowser(final String browser) {
        return Arrays.stream(SupportedBrowser.values())
                .filter(o -> o.name().equals(browser.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new RuntimeException(browser + " is not supported"));
    }
}
