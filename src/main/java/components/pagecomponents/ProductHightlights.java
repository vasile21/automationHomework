/**
 * Created by vasilev on 30/03/2019.
 */
package components.pagecomponents;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import components.notifications.NotificationWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static driverprovider.DriverInstance.getDriver;
import static driverprovider.WaitProvider.waitProvider;

public class ProductHightlights {
    private static final String HIGHLIGHTS_CONTAINER = "//div[@id='main-container']//div[@class='product-highlights-wrapper']//div[@class='row']";
    private static final String ADAUGA_LA_FAVORITE_XPATH = "//button[contains(@class, 'add-to-favorites')]";
    private static final String FAVORITE_PRODUCT = "//i[contains(@class, 'em-fav_fill')]";

    @FindBy(xpath = HIGHLIGHTS_CONTAINER)
    private WebElement highlightsContainer;

    private static final Logger LOGGER = LogManager.getLogger(ProductHightlights.class);

    public ProductHightlights() {
        PageFactory.initElements(getDriver(), this);
    }

    public NotificationWindow addProductToWishList() {
        waitProvider().until(ExpectedConditions.elementToBeClickable(highlightsContainer));

        WebElement addFavorite = highlightsContainer.findElement(By.xpath(ADAUGA_LA_FAVORITE_XPATH));

        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", addFavorite);

       // Actions actions = new Actions(getDriver());
        //actions.moveToElement(addFavorite).click().perform();
       executor.executeScript("arguments[0].click();", addFavorite);

        return new NotificationWindow();
    }

    public boolean isFavoriteIconDisplayed() {
        waitProvider().until(ExpectedConditions.elementToBeClickable(highlightsContainer));

        boolean isIconDisplayed = false;
        try {
            WebElement favoriteIcon = highlightsContainer.findElement(By.xpath(FAVORITE_PRODUCT));
            isIconDisplayed = favoriteIcon.isDisplayed();
        } catch (ElementNotFoundException e) {
            isIconDisplayed = false;
        }

        return isIconDisplayed;
    }
}
