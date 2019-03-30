/**
 * Created by vasilev on 28/03/2019.
 */
package components.menus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static driverprovider.DriverInstance.getDriver;
import static driverprovider.WaitProvider.waitProvider;

public class UserMenu {
    private static final String USER_MENU_CONTAINER = "//div[contains(@class, 'navbar-drop')]";
    private static final String USER_GREET_MESSAGE_XPATH = "//div[contains(@class, 'navbar-drop')]//p//strong[contains(text(), '%s')]";
    private static final String USER_MENU_OPTION_XPATH = "//div[@class = 'ph-dropdown-inner']//a[text()= '%s']";

    @FindBy(xpath = USER_MENU_CONTAINER)
    private WebElement userMenuContainer;

    private static final Logger LOGGER = LogManager.getLogger(UserMenu.class);

    public UserMenu() {
        PageFactory.initElements(getDriver(), this);
    }

    public String getUserGreetMessage(String username) {
        waitProvider().until(ExpectedConditions.visibilityOf(userMenuContainer));

        // construct the xpath
        String userNameXpath = String.format(USER_GREET_MESSAGE_XPATH, username);
        WebElement userNameElement = userMenuContainer.findElement(By.xpath(userNameXpath));

        return userNameElement.getText();
    }

    public void clickOnOption(String userMenuOption) {
        waitProvider().until(ExpectedConditions.visibilityOf(userMenuContainer));

        String userMenuOptionXpath = String.format(USER_MENU_OPTION_XPATH, userMenuOption);
        WebElement userMenuOptionElement = userMenuContainer.findElement(By.xpath(userMenuOptionXpath));

        //userMenuOptionElement.click();
        Actions action = new Actions(getDriver());
        action.moveToElement(userMenuOptionElement).perform();

        waitProvider().until(ExpectedConditions.elementToBeClickable(userMenuOptionElement));
        userMenuOptionElement.click();

        waitProvider().until(ExpectedConditions.invisibilityOf(userMenuOptionElement));
        LOGGER.info("User was logged out");
    }
}