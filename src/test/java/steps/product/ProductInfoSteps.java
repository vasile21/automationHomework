/**
 * Created by vasilev on 30/03/2019.
 */
package steps.product;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import steps.ProductPagesContainer;

public class ProductInfoSteps {

    private ProductPagesContainer productContainer;

    public ProductInfoSteps(ProductPagesContainer productContainer) {
        this.productContainer = productContainer;
    }

    @Then("^I click on product option \"([^\"]*)\"$")
    public void addProductToWishList(String productOption) throws Throwable {
        productContainer.productPage.addProductToWishList(productOption);
    }

    @Then("^I verify that \"([^\"]*)\" messgae is displayed$")
    public void verifyProductNotificationMessage(String notifMessage) throws Throwable {
        boolean isMessageDisplayed = productContainer.productPage.isNotificationMessageDisplayed(notifMessage);

        Assert.assertTrue("Message: " + notifMessage + " was not displayed", isMessageDisplayed);
    }

    @And("^I verify that added to favorites icon is displayed$")
    public void verifyFavoritesIconAppears() {
       productContainer.productPage.isFavoritesIconDisplayed();
    }
}
