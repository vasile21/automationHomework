/**
 * Created by vasilev on 30/03/2019.
 */
package testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import testrunners.config.BaseSeleniumConfigurator;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/product/"
        },
        glue = {
                "steps.base",
                "steps.hooks",
                "steps.login",
                "steps.useraccount",
                "steps.product"
        },
        strict = true,
        format  = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json"}
)

public class WishlistTest extends BaseSeleniumConfigurator {

}
