/**
 * Created by vasilev on 28/03/2019.
 */
package steps.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import driverprovider.DriverInstance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import steps.UserPagesContainer;
import testrunners.LoginTest;

public class LoginHooks {
    private UserPagesContainer container;

    public LoginHooks(UserPagesContainer container) {
        this.container = container;
    }

    private static final Logger LOGGER = LogManager.getLogger(LoginTest.class);

    @Before
    public static void beforeScenario() {
        LOGGER.info("Initialize selenium webdriver");
        DriverInstance.getDriver();
    }

    @After
    public static void afterScenario() {
        // need to quit after each test so I can clean the browser profile
        LOGGER.info("Quit selenium webdriver");
        DriverInstance.quitDriver();
    }
}