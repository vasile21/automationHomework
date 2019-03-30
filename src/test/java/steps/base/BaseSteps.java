/**
 * Created by vasilev on 28/03/2019.
 */
package steps.base;

import cucumber.api.java.en.Given;
import pages.EmagHomePage;
import pages.ProductPage;
import properties.PropertiesConfig;
import steps.ProductPagesContainer;
import steps.UserPagesContainer;

import static driverprovider.DriverInstance.getDriver;
import static properties.PropertiesKeys.HOME_ADDRESS;

public class BaseSteps {
    private UserPagesContainer userContainer;
    private ProductPagesContainer productContainer;

    public BaseSteps(UserPagesContainer userContainer, ProductPagesContainer productContainer){
        this.productContainer = productContainer;
        this.userContainer = userContainer;
    }

    @Given("^I navigate to Emag home page$")
    public void navigateToHomePage()  {
        String baseUrl = PropertiesConfig.getProperty(HOME_ADDRESS);
       getDriver().get(baseUrl);
       userContainer.emagHomePage = new EmagHomePage();
    }

    @Given("^I navigate to the \"([^\"]*)\" product$")
    public void navigateToProductByLink(String url) throws Throwable {
        getDriver().get(url);
        productContainer.productPage = new ProductPage();
    }
}
