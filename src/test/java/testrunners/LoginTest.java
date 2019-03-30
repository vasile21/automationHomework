/**
 * Created by vasilev on 28/03/2019.
 */
package testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import testrunners.config.BaseSeleniumConfigurator;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/login/"
        },
        glue = {
                "steps.base",
                "steps.hooks",
                "steps.login",
                "steps.useraccount",
        },
        strict = true,
        plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json"}
)
public class LoginTest extends BaseSeleniumConfigurator {
    private static final Logger LOGGER = LogManager.getLogger(LoginTest.class);

}
