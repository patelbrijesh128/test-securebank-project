package com.securebank.config;

public final class Config {
    private Config() {}

    public static String baseUrl() {
        return System.getProperty("baseUrl",
                System.getenv().getOrDefault("BANK_BASE_URL", "https://qaplayground.com/bank/login"));
    }
    public static String username() {
        return System.getProperty("bank.username",
                System.getenv().getOrDefault("BANK_USERNAME", "demo"));
    }
    public static String password() {
        return System.getProperty("bank.password",
                System.getenv().getOrDefault("BANK_PASSWORD", "demo"));
    }
    public static boolean headed() {
        return Boolean.parseBoolean(System.getProperty("headed", "false"));
    }
}
