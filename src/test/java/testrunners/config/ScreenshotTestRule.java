/**
 * Created by vasilev on 30/03/2019.
 */
package testrunners.config;

import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;


import static driverprovider.DriverInstance.getDriver;

class ScreenshotTestRule extends TestWatcher {

    private WebDriver webDriver;


    ScreenshotTestRule(WebDriver webDriver) {
        this.webDriver = getDriver();
    }

    @Override
    protected void failed(Throwable e, Description description) {
        String methodName = description.getMethodName();
        String fileName = description.getTestClass().getSimpleName() + "-" + methodName + ".png";

        try {
            File destiny = new File(fileName);
            FileUtils.copyFile(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE), destiny);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}