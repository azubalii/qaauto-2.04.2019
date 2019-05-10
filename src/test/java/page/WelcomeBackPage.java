package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomeBackPage extends BasePage {
    @FindBy(xpath = "//input[@id='username']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInButton;
    @FindBy(xpath = "//div[@id='error-for-username']")
    private WebElement userEmailValidationMessage;
    @FindBy(xpath = "//div[@id='error-for-password']")
    private WebElement userPasswordValidationMessage;

    public WelcomeBackPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return passwordField.isDisplayed()
                && emailField.isDisplayed()
                && signInButton.isDisplayed();
    }

    public String getUserEmailValidationMessage() {
        return userEmailValidationMessage.getText();
    }

    public String getUserPasswordValidationMessage() {
        return userPasswordValidationMessage.getText();
    }
}
