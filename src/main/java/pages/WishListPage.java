/**
 * Created by vasilev on 30/03/2019.
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static driverprovider.DriverInstance.getDriver;
import static driverprovider.WaitProvider.waitProvider;

public class WishListPage {
    private static final String WISHLIST_CONTAINER_XPATH = "//table[@class = 'wishlist-box']";
    private static final String WISHLIST_PRODUCTS_XPATH = "//tr//td[@class ='sc']//div[@class='product-title']//a";
    private static final String WISHLIST_PRODUCT_XPATH = "//tr//td[@class ='sc']//div[@class='product-title']//a[contains(@title, '%s')]";
    private static final String WISHLIST_PRODUCT_CHECK = "/../../../..//td[@class='fa']//input[@type ='checkbox']";

    private static final String DELETE_WISHLIST_XPATH = "//button[@id = 'removeFromWishlist']";

    @FindBy(xpath = WISHLIST_CONTAINER_XPATH)
    private WebElement wishListContainer;

    @FindBy(xpath = DELETE_WISHLIST_XPATH)
    private WebElement deleteButton;

    public WishListPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public List<String> getWishListProducts() {
        List<String> products = new ArrayList<>();

        List<WebElement> productsList = wishListContainer.findElements(By.xpath(WISHLIST_PRODUCTS_XPATH));
        for (WebElement currentProduct : productsList) {
            products.add(currentProduct.getText());
        }
        return products;
    }

    public void selectProductInWishlist(String productTitle) {
        String productNameXpath = String.format(WISHLIST_PRODUCT_XPATH, productTitle);

        String checkBoxXpath = productNameXpath + WISHLIST_PRODUCT_CHECK;
        WebElement checkBoxElement = wishListContainer.findElement(By.xpath(checkBoxXpath));
        waitProvider().until(ExpectedConditions.visibilityOf(checkBoxElement));

        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].click();", checkBoxElement);


    }

    public void pressDeleteButton() {
        waitProvider().until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
    }
}
