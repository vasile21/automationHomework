/**
 * Created by vasilev on 28/03/2019.
 */
package pages;

import components.forms.baseforms.BaseForm;
import components.forms.CheckboxStatus;
import components.forms.SocialMediaLoginForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.interactions.Actions;

import static driverprovider.DriverInstance.getDriver;

public class LoginPage extends BaseForm {
    private static final Logger LOGGER = LogManager.getLogger(LoginPage.class);

    private BaseForm loginForm;
    private SocialMediaLoginForm socialMediaLoginForm;

    public LoginPage() {
        loginForm = new BaseForm();
        socialMediaLoginForm = new SocialMediaLoginForm();
    }

    public void setLoginInputField(String emailFieldName, String emailValue) {
        LOGGER.debug("Username for login is:" + emailValue);
        loginForm.setFieldValue(emailFieldName, emailValue);
    }

    public UserHomePage clickContinueValidPassword(String buttonName) {
        loginForm.clickButton(buttonName);
        return new UserHomePage();
    }

    public void clickContinueInvalidPassword(String buttonName) {
        loginForm.clickButton(buttonName);
    }

    public boolean verifyIfErrorMessageAppears(String errorMessage) {
        return loginForm.isWideErrorMessageDisplayed(errorMessage);
    }

    public void checkboxStatusChange(CheckboxStatus status, String checboxId) {
        loginForm.setCheckboxStatus(status.getCheckboxStatus(), checboxId);
    }

    public String getValueFromEmailField(String emailField) {
        return loginForm.getFieldValue(emailField);
    }

    public void clickOnSocialPlatformLink(String socialPlatform) {
        loginForm.clickOnFormLink(socialPlatform);
    }

    public void setGoogleEmailAccount(String googleEmailAccount) {
        socialMediaLoginForm.googleEmailAccount(googleEmailAccount);
    }

    public void pressNextButtonGooglePassword() {
        socialMediaLoginForm.pressNextGoogleButtonForPassword();
    }

    public void setGooglePassword(String googlePassword) {
        socialMediaLoginForm.googlePassword(googlePassword);
    }

    public UserHomePage pressNextButtonGoogleLogin() {
         return socialMediaLoginForm.pressNextGoogleLogin();
    }
}