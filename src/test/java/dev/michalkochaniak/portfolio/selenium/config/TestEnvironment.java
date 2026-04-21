package dev.michalkochaniak.portfolio.selenium.config;

public final class TestEnvironment {
    private TestEnvironment() {
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "true"));
    }
}
