/**
 * Created by vasilev on 28/03/2019.
 */
package steps.login;

import components.forms.CheckboxStatus;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import optionconstants.UserMenuOptions;
import org.junit.Assert;
import steps.UserPagesContainer;

import java.util.Set;

import static driverprovider.DriverInstance.getDriver;

public class LoginSteps {

    private UserPagesContainer container;

    public LoginSteps(UserPagesContainer container) {
        this.container = container;
    }

    @Given("^I navigate to the login page$")
    public void navigatetoLoginPage() {
        container.loginPage = container.emagHomePage.navigateToLoginPage();
    }

    @When("^I set \"([^\"]*)\" adress field to \"([^\"]*)\"$")
    public void setEmailAddress(String emailField, String emailAddress) throws Throwable {
        container.loginPage.setLoginInputField(emailField, emailAddress);
    }

    @And("^I press \"([^\"]*)\" button$")
    public void pressContinueButton(String buttonText) {
        container.loginPage.clickContinueValidPassword(buttonText);
    }

    @And("^I press \"([^\"]*)\" button after valid password$")
    public void pressContinueAfterValidPassword(String textButton) throws Throwable {
        container.userHomePage = container.loginPage.clickContinueValidPassword(textButton);
    }

    @And("^I set \"([^\"]*)\" field to \"([^\"]*)\"$")
    public void setPasswordField(String passwordField, String passwordValue) throws Throwable {
        container.loginPage.setLoginInputField(passwordField, passwordValue);
    }

    @And("^I \"([^\"]*)\" box \"([^\"]*)\" option$")
    public void modifyRememberMe(CheckboxStatus status, String checboxId) throws Throwable {
        container.loginPage.checkboxStatusChange(status, checboxId);
    }

    @And("^I press \"([^\"]*)\" button after invalid password$")
    public void pressContinueAfterInvalidPassword(String buttonText) throws Throwable {
        container.loginPage.clickContinueInvalidPassword(buttonText);
    }

    @Then("^I verify that \"([^\"]*)\" error message appears$")
    public void verifyLoginWrongPasswordErrorMessage(String errorMessage) throws Throwable {
        boolean isErrorDisplayed = container.loginPage.verifyIfErrorMessageAppears(errorMessage);

        Assert.assertTrue(errorMessage + " was not displayed", isErrorDisplayed);
    }

    @Then("^I verify if value from \"([^\"]*)\" adress field is \"([^\"]*)\"$")
    public void checkValueFromEmailAdressField(String emailField, String emailAddress) throws Throwable {
        String actualValue = container.loginPage.getValueFromEmailField(emailField);
        Assert.assertEquals("Email values was not saved", emailAddress, actualValue);
    }

    @And("^I logout from application$")
    public void logoutFromApplication() throws Throwable {
        container.emagHomePage.logout(UserMenuOptions.LOG_OUT);
    }

    @When("^I click on \"([^\"]*)\" login option$")
    public void clickSocialLoginOption(String socialPlatform) throws Throwable {
        container.loginPage.clickOnSocialPlatformLink(socialPlatform);
    }

    @And("^I set \"([^\"]*)\" as google email$")
    public void setGoogleEmail(String googleEmail) throws Throwable {
        container.loginPage.setGoogleEmailAccount(googleEmail);
    }

    @And("^I press Next button on google login for password$")
    public void pressNextGoogleLoginButtonForPassword() throws Throwable {
        container.loginPage.pressNextButtonGooglePassword();
    }

    @And("^I set \"([^\"]*)\" as google password$")
    public void setGooglePassword(String googlePassword) throws Throwable {
       container.loginPage.setGooglePassword(googlePassword);
    }

    @And("^I press Next button on google login form with window focus change")
    public void pressNextGoogleLoginButton() throws Throwable {
        container.userHomePage = container.loginPage.pressNextButtonGoogleLogin();
    }

    @And("^I log in into application with \"([^\"]*)\" email and \"([^\"]*)\" password$")
    public void loginIntoApplication(String emailAddress, String password) throws Throwable {
        container.loginPage = container.emagHomePage.navigateToLoginPage();
        container.loginPage.setLoginInputField("email", emailAddress);
        container.loginPage.clickContinueValidPassword("Continua");
        container.loginPage.setLoginInputField("password", password);
        container.loginPage.clickContinueValidPassword("Continua");
    }
}