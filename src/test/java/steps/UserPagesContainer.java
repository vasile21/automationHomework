/**
 * Created by vasilev on 28/03/2019.
 */
package steps;

import driverprovider.DriverInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.DriverProvider;
import pages.EmagHomePage;
import pages.LoginPage;
import pages.UserHomePage;
import pages.WishListPage;

public class UserPagesContainer {
    public UserHomePage userHomePage;
    public LoginPage loginPage;
    public EmagHomePage emagHomePage;
    public WishListPage wishListPage;
}
