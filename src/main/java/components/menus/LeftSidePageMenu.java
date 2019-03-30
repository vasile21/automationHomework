/**
 * Created by vasilev on 28/03/2019.
 */
package components.menus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static driverprovider.DriverInstance.getDriver;

public class LeftSidePageMenu {
    //todo check if this is is not the container
    private static final String MENU_ITEMS_XPATH = "//div[@class='megamenu-list-container']//ul[@class='megamenu-list']//li";
    private static final String MENU_ITEM_XPATH = "//div[@class='megamenu-list-container']//ul[@class='megamenu-list']//li//a//span[text()='%s']";

    @FindBy(xpath = MENU_ITEMS_XPATH)
    private WebElement menuItems;

    public LeftSidePageMenu() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean menuItemIsDisplayed(String itemText) {
        final String menuItemXpath = String.format(MENU_ITEM_XPATH, itemText);
        final WebElement menuItem = getDriver().findElement(By.xpath(menuItemXpath));
        return menuItem.isDisplayed();
    }

    public void clickOnMenuItem(String itemText) {
        final String menuItemXpath = String.format(MENU_ITEM_XPATH, itemText);
    }
}