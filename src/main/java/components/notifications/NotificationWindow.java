/**
 * Created by vasilev on 30/03/2019.
 */
package components.notifications;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import gherkin.lexer.Pa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static driverprovider.DriverInstance.getDriver;
import static driverprovider.WaitProvider.waitProvider;

public class NotificationWindow {
    private static final String NOTIFICATION_CONTAINER_XPATH = "//div[contains(@class, 'ns-wrap')]";
    private static final String NOTIFICATION_MESSAGE = "//div[contains(@class, 'table-cell') and contains(text(), '%s')]";

    @FindBy(xpath = NOTIFICATION_CONTAINER_XPATH)
    private WebElement noficationConainer;

    private static final Logger LOGGER = LogManager.getLogger(NotificationWindow.class);

    public NotificationWindow() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean isMessageDisplayed(String notifMessage) {
        waitProvider().until(ExpectedConditions.visibilityOf(noficationConainer));

        boolean isMessageDisplayed = false;
        try {
            String messageXpath = String.format(NOTIFICATION_MESSAGE, notifMessage);
            WebElement messageElement = getDriver().findElement(By.xpath(messageXpath));

            isMessageDisplayed = messageElement.isDisplayed();

        } catch (NoSuchElementException e) {
            LOGGER.info("Web element with message: " + notifMessage + " was not displayed");
        }

        return isMessageDisplayed;
    }
}
