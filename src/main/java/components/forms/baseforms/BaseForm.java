/**
 * Created by vasilev on 28/03/2019.
 */
package components.forms.baseforms;

import components.forms.CheckboxStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static driverprovider.DriverInstance.getDriver;
import static driverprovider.WaitProvider.waitProvider;

public class BaseForm {
    private static final String FORM_CONTAINER_XPATH = "//form[contains(@class, '-gui-form')]";
    private static final String FORM_INPUT_FIELD_XPATH = "//div[@class ='gui-form-row']//input[@id = '%s']";
    private static final String FORM_BUTTON_XPATH = "//button[contains(text(),'%s')]";
    private static final String FORM_ERROR_MESSAGE_PATH = "//div[contains(@class, 'custom_error_message')]//div[contains(@class, 'gui-form-control')]//span[text() ='%s']";
    private static final String INPUT_TYPE_CHECKBOX_XPATH = "//input[@type='checkbox' and @id= '%s']";
    private static final String FORM_LINK_XPATH = "//div[contains(@class,gui-form-row)]//a[@title='%s']";

    private static final Logger LOGGER = LogManager.getLogger(BaseForm.class);

    @FindBy(xpath = FORM_CONTAINER_XPATH)
    private WebElement formContainer;

    public BaseForm() {
        PageFactory.initElements(getDriver(), this);
    }

    public void setFieldValue(String fieldName, String fieldValue) {
        // construct the xpath
        String inputFieldXpath = String.format(FORM_INPUT_FIELD_XPATH, fieldName);
        try {
            WebElement formInputField = formContainer.findElement(By.xpath(inputFieldXpath));

            // set value
            formInputField.clear();
            formInputField.sendKeys(fieldValue);

            // wait for value to be displayed in input field
            waitProvider().until(ExpectedConditions.attributeContains(formInputField, "value", fieldValue));
        } catch (InvalidElementStateException e) {
            JavascriptExecutor jse = (JavascriptExecutor) getDriver();
            jse.executeScript("arguments[0].value = '" + fieldValue + "'", getDriver().findElement(By.xpath(inputFieldXpath)));
        }
    }

    public String getFieldValue( String fieldName) {
        // wait for form to be visible
        waitProvider().until(ExpectedConditions.elementToBeClickable(formContainer));

        String inputFieldXpath = String.format(FORM_INPUT_FIELD_XPATH, fieldName);
        WebElement formInputField = formContainer.findElement(By.xpath(inputFieldXpath));

        return formInputField.getAttribute("value");
    }

    public void clickButton(String buttonName) {
        String buttonXpath = String.format(FORM_BUTTON_XPATH, buttonName);
        WebElement formButton = formContainer.findElement(By.xpath(buttonXpath));
        waitProvider().until(ExpectedConditions.elementToBeClickable(formButton));

        formButton.click();
    }

    public boolean isWideErrorMessageDisplayed(String errorMessage) {
        String errorMessageXpath = String.format(FORM_ERROR_MESSAGE_PATH, errorMessage);
        waitProvider().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(errorMessageXpath)));

        WebElement formInputField = formContainer.findElement(By.xpath(errorMessageXpath));
        return formInputField.isDisplayed();
    }

    public void setCheckboxStatus(String checkboxStatus, String checboxId) {
        String checkboxXpath = String.format(INPUT_TYPE_CHECKBOX_XPATH, checboxId);
        if (formContainer.findElement(By.xpath(checkboxXpath)).isDisplayed()) {
            WebElement checkBox = formContainer.findElement(By.xpath(checkboxXpath));

            boolean isCheckboxSelected = checkBox.isSelected();
            if (isCheckboxSelected && checkboxStatus.equals(CheckboxStatus.UNCHECK.getCheckboxStatus())) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].checked = false;", checkBox);
            } else if (!isCheckboxSelected && checkboxStatus.equals(CheckboxStatus.CHECK.getCheckboxStatus())) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].checked = true;", checkBox);
            }
        } else {
            LOGGER.info("Checkbox " + checboxId + " was not displayed");
        }
    }

    public void clickOnFormLink(String linkTitle) {
        String linkXpath = String.format(FORM_LINK_XPATH, linkTitle);

        waitProvider().until(ExpectedConditions.elementToBeClickable(formContainer));
        WebElement link = formContainer.findElement(By.xpath(linkXpath));
        link.click();
    }
}