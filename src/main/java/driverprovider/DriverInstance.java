/**
 * Created by vasilev on 28/03/2019.
 */
package driverprovider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import properties.PropertiesConfig;
import utils.FileUtilities;

import java.io.File;

import static properties.PropertiesKeys.*;

public class DriverInstance {
    private static WebDriver driver;
    // BROWSER_TYPE_CHROME or BROWSER_TYPE_FIREFOX
    private static final String BROWSER = PropertiesConfig.getProperty(BROWSER_TYPE_CHROME);
    private static final String CHROME_DIRECTORY_PROFILE = PropertiesConfig.getProperty(CHROME_PROFILE_DIRECTORY);
    private static final String FIREFOX_DIRECTORY_PROFILE = PropertiesConfig.getProperty(FIREFOX_PROFILE_DIRECTORY);
    private static final String USER_DIR_PATH = System.getProperty("user.dir");

    private DriverInstance() {
    }


    public static WebDriver getDriver() {
        switch (BROWSER) {
            case "chrome":
                if (driver == null) {
                    System.setProperty("webdriver.chrome.driver", "src\\main\\drivers\\chromedriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-extensions");
                    options.addArguments("disable-infobars");
                    options.addArguments("--disable-notifications");
                    System.getProperty("user.dir");
                    String chromeProfile = USER_DIR_PATH + CHROME_DIRECTORY_PROFILE;
                    options.addArguments("user-data-dir=" + chromeProfile);

                    driver = new ChromeDriver(options);
                }
                break;
            case "firefox":
                if (driver == null) {
                    System.setProperty("webdriver.gecko.driver", "src\\main\\drivers\\geckodriver_24.exe");

                   // FirefoxOptions options = new FirefoxOptions();
                   // options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");

                  //  DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                  //  capabilities.setCapability("moz:firefoxOptions", options);
                  //  capabilities.setCapability("networkConnectionEnabled", true);
                 //   capabilities.setCapability("browserConnectionEnabled", true);

                    driver = new FirefoxDriver();

                    driver.manage().window().maximize();
                }
                break;
            default:
                throw new IllegalStateException("No driver found for provided value: " + BROWSER);
        }

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

        if (BROWSER.equals("chrome")) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // clean cache file from custom chrome profile
            final String chromeProfileDirectory = PropertiesConfig.getProperty(CHROME_PROFILE_DIRECTORY);
            FileUtilities.deleteAllFilesFromDirectory(USER_DIR_PATH + chromeProfileDirectory);
        }
    }
}