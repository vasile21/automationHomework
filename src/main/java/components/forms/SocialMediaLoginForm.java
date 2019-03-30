/**
 * Created by vasilev on 29/03/2019.
 */
package components.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.UserHomePage;

import java.util.Set;

import static driverprovider.DriverInstance.getDriver;
import static driverprovider.WaitProvider.waitProvider;

public class SocialMediaLoginForm {

    private Set windowHandles;

    private static final String GOOGLE_FORM_CONTAINER_XPATH = "//div[@id='view_container']";
    private static final String GOOGLE_EMAIL_FIELD_XPATH = "//input[@id='identifierId']";
    private static final String GOOGLE_NEXT_BUTTON_XPATH = "//div[@role='button']//span[text()='Înainte']";
    private static final String GOOGLE_PASSWORD_FIELD = "input[type=password]";

    @FindBy(xpath = GOOGLE_FORM_CONTAINER_XPATH)
    private WebElement googleLoginForm;

    public SocialMediaLoginForm() {
        PageFactory.initElements(getDriver(), this);
    }

    public void googleEmailAccount(String googleEmail) {
        // connect to facebook opens a new window in the same tab, in addition to the login popup.
        // Store all open window handles
        windowHandles = getDriver().getWindowHandles();
        // Switch focus to the second opened window (which doesn't have a title)
        getDriver().switchTo().window((String) windowHandles.toArray()[1]);

        waitProvider().until(ExpectedConditions.elementToBeClickable(By.xpath(GOOGLE_EMAIL_FIELD_XPATH)));

        WebElement googleAddress = getDriver().findElement(By.xpath(GOOGLE_EMAIL_FIELD_XPATH));
        googleAddress.click();
        googleAddress.clear();
        googleAddress.sendKeys(googleEmail);

        // wait for value to be displayed in input field
        waitProvider().until(ExpectedConditions.attributeContains(googleAddress, "value", googleEmail));
    }

    public void pressNextGoogleButtonForPassword() {
        waitProvider().until(ExpectedConditions.elementToBeClickable(By.xpath(GOOGLE_NEXT_BUTTON_XPATH)));
        WebElement googleNextButton = getDriver().findElement(By.xpath(GOOGLE_NEXT_BUTTON_XPATH));
        googleNextButton.click();
    }

    public void googlePassword(String googlePassword) {
        waitProvider().until(ExpectedConditions.elementToBeClickable(By.cssSelector(GOOGLE_PASSWORD_FIELD)));

        WebElement googlePasswordField = getDriver().findElement(By.cssSelector(GOOGLE_PASSWORD_FIELD));
        googlePasswordField.click();
        googlePasswordField.clear();
        googlePasswordField.sendKeys(googlePassword);

        // wait for value to be displayed in input field
        waitProvider().until(ExpectedConditions.attributeContains(googlePasswordField, "value", googlePassword));
    }

    public UserHomePage pressNextGoogleLogin() {
        waitProvider().until(ExpectedConditions.elementToBeClickable(By.xpath(GOOGLE_NEXT_BUTTON_XPATH)));
        WebElement googleNextButton = getDriver().findElement(By.xpath(GOOGLE_NEXT_BUTTON_XPATH));
        googleNextButton.click();

        // switch back to the original window
        getDriver().switchTo().window((String) windowHandles.toArray()[0]);

        waitProvider().until((ExpectedCondition<Boolean>) wd ->
               ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        // above "ready state seems to not work"
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new UserHomePage();
    }
}
