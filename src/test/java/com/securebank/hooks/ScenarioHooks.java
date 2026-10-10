package com.securebank.hooks;

import com.securebank.driver.BrowserManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ScenarioHooks {
    @Before
    public void beforeScenario() {
        BrowserManager.start();
    }

    @After
    public void afterScenario() {
        BrowserManager.stop();
    }
}
