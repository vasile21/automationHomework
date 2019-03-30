/**
 * Created by vasilev on 28/03/2019.
 */
package pages;

import components.menus.TopHorizontalMenu;
import components.menus.UserMenu;

public class UserHomePage {
    private TopHorizontalMenu topHorizontalMenu;
    private UserMenu userMenu;

    public UserHomePage() {
        topHorizontalMenu = new TopHorizontalMenu();
        userMenu = new UserMenu();
    }
    public String greetMessage(String userName) {
        return userMenu.getUserGreetMessage(userName);
    }
}