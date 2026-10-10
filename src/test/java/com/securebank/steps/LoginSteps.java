package com.securebank.steps;

import com.securebank.config.Config;
import com.securebank.driver.BrowserManager;
import com.securebank.pages.LoginPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LoginSteps {
    private LoginPage loginPage;

    private LoginPage loginPage() {
        if (loginPage == null) loginPage = new LoginPage(BrowserManager.page());
        return loginPage;
    }

    @Given("the user opens the SecureBank login page")
    public void openLoginPage() {
        loginPage().open();
        Assert.assertTrue(loginPage().isLoginFormVisible(),
                "Login form was not detected. Update LoginPage locators for the live page.");
    }

    @When("the user submits valid configured credentials")
    public void submitConfiguredCredentials() {
        loginPage().login(Config.username(), Config.password());
    }

    @When("the user submits username {string} and password {string}")
    public void submitCredentials(String username, String password) {
        loginPage().login(username, password);
    }

    @Then("the login page should be displayed")
    public void loginPageShouldBeDisplayed() {
        Assert.assertTrue(loginPage().isLoginFormVisible(),
                "Expected login form to remain visible.");
    }

    @Then("a login error or validation message should be displayed")
    public void loginErrorOrValidationShouldBeDisplayed() {
        String feedback = loginPage().visibleFeedback();
        String body = loginPage().pageText().toLowerCase();
        boolean hasFeedback = !feedback.isBlank()
                || body.contains("invalid")
                || body.contains("incorrect")
                || body.contains("required")
                || body.contains("error")
                || body.contains("failed");
        Assert.assertTrue(hasFeedback,
                "No known login error/validation message found. Review the application's actual response.");
    }

    @Then("the page should show a post-login view or navigate away from login")
    public void postLoginViewOrNavigateAway() {
        boolean navigatedAway = !loginPage().currentUrl().contains("/bank/login");
        String body = loginPage().pageText().toLowerCase();
        boolean likelyDashboard = body.contains("dashboard")
                || body.contains("account")
                || body.contains("balance")
                || body.contains("welcome")
                || body.contains("logout");
        Assert.assertTrue(navigatedAway || likelyDashboard,
                "No post-login indicator detected. Verify test credentials and update the expected dashboard assertion.");
    }
}
