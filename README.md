# SecureBank UI automation starter

Java 17 + Maven + Playwright Java + TestNG + Cucumber/Gherkin + Page Object Model (POM).

**Target:** https://qaplayground.com/bank/login

> The target page is JavaScript-rendered and its live form selectors/valid credentials could not be verified from the static page snapshot. This starter uses resilient locator fallbacks and configurable credentials. Confirm the selectors and expected success/error text against the current page before treating the tests as production-ready.

## Prerequisites
- JDK 17+
- Maven 3.9+
- Internet access on first build (Maven dependencies and browser binaries)

## Run
```bash
mvn clean test
```
Install the Chromium browser if needed:
```bash
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```
Run headed:
```bash
mvn clean test -Dheaded=true
```
Override URL / credentials:
```bash
mvn clean test -DbaseUrl=https://qaplayground.com/bank/login -Dbank.username=YOUR_USERNAME -Dbank.password=YOUR_PASSWORD
```

Reports: `target/cucumber-report.html` and `target/cucumber-report.json`.

## Project structure
```
src/test/java/com/brijesh/securebank/
  config/Config.java
  driver/BrowserManager.java
  hooks/ScenarioHooks.java
  pages/BasePage.java
  pages/LoginPage.java
  steps/LoginSteps.java
  runners/TestRunner.java
src/test/resources/features/login.feature
src/test/resources/testng.xml
```

## Design notes
- `LoginPage` owns UI locators and page actions (POM).
- `LoginSteps` maps Gherkin steps to page-object calls.
- Cucumber hooks create/close Playwright per scenario.
- TestNG runs the Cucumber suite; Surefire selects `TestRunner`.
- Credentials are supplied via system properties/environment, not hard-coded.
- If the app requires a CAPTCHA, MFA, or other interactive verification, use an approved test environment/test bypass provided by the site owner; do not try to evade it.

## First maintenance task
Open the login page, inspect the username/password input labels and the login button, then update `LoginPage.java` locator fallbacks and the success/error message assertions to match the live application.
