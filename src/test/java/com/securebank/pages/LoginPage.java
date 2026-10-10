package com.securebank.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.securebank.config.Config;

public class LoginPage extends BasePage {
    public LoginPage(Page page)
            {
                super(page);
            }

    public void open() {
        page.navigate(Config.baseUrl());
    }

    private Locator usernameField() {
        return firstVisible(
            page.getByLabel("Username", new Page.GetByLabelOptions().setExact(false)),
            page.getByLabel("User Name", new Page.GetByLabelOptions().setExact(false)),
            page.getByPlaceholder("Username", new Page.GetByPlaceholderOptions().setExact(false)),
            page.locator("input[name='username'], input[name='userName'], input[id*='username' i], input[id*='user' i], input[type='text']").filter(new Locator.FilterOptions())
        );
    }

    private Locator passwordField() {
        return firstVisible(
            page.getByLabel("Password", new Page.GetByLabelOptions().setExact(false)),
            page.getByPlaceholder("Password", new Page.GetByPlaceholderOptions().setExact(false)),
            page.locator("input[type='password']")
        );
    }

    private Locator loginButton() {
        return firstVisible(
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login").setExact(false)),
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in").setExact(false)),
            page.locator("button[type='submit'], input[type='submit']")
        );
    }

    public void login(String username, String password) {
        usernameField().fill(username);
        passwordField().fill(password);
        loginButton().click();
    }

    public boolean isLoginFormVisible() {
        try {
            return usernameField().isVisible() && passwordField().isVisible();
        } catch (AssertionError | RuntimeException e) {
            return false;
        }
    }

    public String currentUrl() { return page.url(); }

    public String visibleFeedback() {
        Locator feedback = page.locator("[role='alert'], .alert, .error, .error-message, .toast, .message, [class*='error' i]");
        try {
            for (int i = 0; i < feedback.count(); i++) {
                Locator item = feedback.nth(i);
                if (item.isVisible() && !item.innerText().isBlank()) return item.innerText().trim();
            }
        } catch (RuntimeException ignored) { }
        return "";
    }

    public String pageText() {
        try { return page.locator("body").innerText(); }
        catch (RuntimeException e) { return ""; }
    }
}
