/**
 * Created by vasilev on 28/03/2019.
 */
package testrunners.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.BeforeClass;
import org.junit.Rule;

import java.io.File;

import static driverprovider.DriverInstance.getDriver;

public class BaseSeleniumConfigurator {

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule(getDriver());

    private static final Logger LOGGER = LogManager.getLogger(BaseSeleniumConfigurator.class);
    @BeforeClass
    public static void baseBeforeClass() {
        //todo: only dummy messages are logged, no real selenium/java errors, sry guys :(
        LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\log4j2.xml");
        context.setConfigLocation(file.toURI());
    }
}
