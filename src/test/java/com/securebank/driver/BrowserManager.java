package com.securebank.driver;

import com.microsoft.playwright.*;
import com.securebank.config.Config;

public final class BrowserManager {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    private BrowserManager() {}

    public static void start() {
        playwright = Playwright.create();
        boolean headed = Config.headed();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1440, 900));
        page = context.newPage();
        page.setDefaultTimeout(10_000);
    }

    public static Page page() {
        if (page == null) throw new IllegalStateException("Browser not started. Check Cucumber hooks.");
        return page;
    }

    public static void stop() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        page = null; context = null; browser = null; playwright = null;
    }
}
