package com.securebank.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BasePage {
    protected final Page page;

    protected BasePage(Page page)
    {
        this.page = page;
    }

    protected Locator firstVisible(Locator... candidates) {
        for (Locator locator : candidates) {
            try {
                if (locator.count() > 0 && locator.first().isVisible()) return locator.first();
            } catch (RuntimeException ignored) { /* try next locator */ }
        }
        throw new AssertionError("Could not find a visible element. Check page selectors.");
    }
}
