/**
 * Created by vasilev on 28/03/2019.
 */
package components.menus;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.WishListPage;

import static driverprovider.DriverInstance.getDriver;
import static driverprovider.WaitProvider.waitProvider;

public class TopHorizontalMenu {
    private static final String TOP_HORIZONTAL_MENU_XPATH = "//nav[@id='masthead']";
    private static final String LOGIN_BUTTON_XPATH = "//div[@class='navbar-toolbox']//div[contains(@class, 'btn-group')]//a[@href='/user/login']";
    private static final String USER_MENU_ICON_XPATH = "//a[@id='my_account']//i[@class='caret']";
    private static final String USER_MENU_LOGOUT_ICON = "//a[(@id ='emg-user-menu')]//i";
    private static final String USER_MENU_PAGE_PATH = "//a[(@id ='emg-user-menu')]";
    private static final String USER_FAVORITE_PAGE_XPATH = "//a[@id ='my_wishlist']";

    @FindBy(xpath = TOP_HORIZONTAL_MENU_XPATH)
    private WebElement horizontalMenuContainer;

    @FindBy(xpath = LOGIN_BUTTON_XPATH)
    private WebElement loginButton;

    @FindBy(xpath = USER_MENU_ICON_XPATH)
    private WebElement userMenuIcon;

    @FindBy(xpath = USER_MENU_LOGOUT_ICON)
    private WebElement logoutMenuIcon;

    @FindBy(xpath = USER_MENU_PAGE_PATH)
    private WebElement userHomePageElement;

    @FindBy(xpath = USER_FAVORITE_PAGE_XPATH)
    private WebElement userFavoritePageElement;

    public TopHorizontalMenu() {
        PageFactory.initElements(getDriver(), this);
    }

    public LoginPage clickOnLoginButton() {
        waitProvider().until(ExpectedConditions.visibilityOf(horizontalMenuContainer));
        loginButton.click();
        return new LoginPage();
    }

    public UserMenu openUserMenu() {
        Actions action = new Actions(getDriver());
        action.moveToElement(userMenuIcon).perform();

        return new UserMenu();
    }

    public UserMenu openUserMenuForLogout() {
        Actions action = new Actions(getDriver());
        action.moveToElement(logoutMenuIcon).perform();

        return new UserMenu();
    }

    public WishListPage clickOnFavorite() {
        waitProvider().until(ExpectedConditions.visibilityOf(horizontalMenuContainer));
        userFavoritePageElement.click();

        return new WishListPage();
    }
}