package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class EnterNewPasswordPage extends BasePage {

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;
    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    public EnterNewPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return submitButton.isDisplayed();
    }

    public ResetPasswordSuccessPage submitNewPassword(String password2){
        newPasswordField.sendKeys(password2);
        confirmPasswordField.sendKeys(password2);
        submitButton.click();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResetPasswordSuccessPage(driver);
    }
}
