/**
 * Created by vasilev on 30/03/2019.
 */
package steps.product;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import steps.UserPagesContainer;

import java.util.List;

public class WishlistSteps {

    private UserPagesContainer userPagesContainer;
    private List<String> wishListProducs;

    public WishlistSteps(UserPagesContainer userPagesContainer) {
        this.userPagesContainer = userPagesContainer;
    }


    @Given("^I open Favorite product page$")
    public void navigateToFavoriteProductPage() throws Throwable {
        userPagesContainer.wishListPage = userPagesContainer.emagHomePage.navigatetoWishlistPage();
    }

    @Then("^I veirfy if wishlist contains the following products:$")
    public void iVeirfyIfWishlistContainsTheFollowingProducts(List<String> expectedProducts) throws Throwable {
        wishListProducs = userPagesContainer.emagHomePage.readWishListProductNames();

        Assert.assertTrue("Actual wishlist does not contains all expected products", expectedProducts.containsAll(wishListProducs));
    }

    @Then("^I select \"([^\"]+)\" product from wishlist$")
    public void deleteProductFromWishlist(String procuctTitle) throws Throwable {
      userPagesContainer.wishListPage.selectProductInWishlist(procuctTitle);
    }

    @Then("^I press delete button on wishlist page$")
    public void pressDeleteButton() throws Throwable {
        userPagesContainer.wishListPage.pressDeleteButton();
    }
}
