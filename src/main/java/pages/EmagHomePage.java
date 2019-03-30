/**
 * Created by vasilev on 28/03/2019.
 */
package pages;

import components.menus.LeftSidePageMenu;
import components.menus.TopHorizontalMenu;
import components.menus.UserMenu;
import driverprovider.DriverInstance;
import optionconstants.UserMenuOptions;
import properties.PropertiesConfig;

import java.util.List;

import static driverprovider.DriverInstance.getDriver;
import static properties.PropertiesKeys.HOME_ADDRESS;
import static properties.PropertiesKeys.PERSONAL_DATA;

public class EmagHomePage {
    private LeftSidePageMenu leftSideMenuPage;
    private TopHorizontalMenu topHorizontalMenu;
    private LoginPage loginPage;
    private WishListPage wishListPage;
    private UserMenu userMenu;

    public EmagHomePage() {
        leftSideMenuPage = new LeftSidePageMenu();
        topHorizontalMenu = new TopHorizontalMenu();
        userMenu = new UserMenu();
        wishListPage = new WishListPage();
    }

    public LoginPage navigateToLoginPage() {
        loginPage = topHorizontalMenu.clickOnLoginButton();
        return loginPage;
    }

    public UserMenu openUserAccountMenu() {
        return topHorizontalMenu.openUserMenu();
    }

    public WishListPage navigatetoWishlistPage() {
        wishListPage = topHorizontalMenu.clickOnFavorite();
        return wishListPage;
    }

    public void logout(UserMenuOptions userMenuOption) {
        userMenu.clickOnOption(userMenuOption.getUserMenuOptionValue());
        String currentUrl = getDriver().getCurrentUrl();
        // need to try again due to emag pop-up
        if (currentUrl != PropertiesConfig.getProperty(HOME_ADDRESS)) {
            String baseUrl = PropertiesConfig.getProperty(HOME_ADDRESS);
            String personalDataUrl = PropertiesConfig.getProperty(PERSONAL_DATA);
            DriverInstance.getDriver().get(baseUrl + personalDataUrl);
            openMenuForLogut();
            userMenu.clickOnOption(userMenuOption.getUserMenuOptionValue());
        }
    }

    private void openMenuForLogut() {
        topHorizontalMenu.openUserMenuForLogout();
    }

    public List<String> readWishListProductNames() {
        return wishListPage.getWishListProducts();
    }
}
