package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class ResetPasswordPage extends BasePage {
    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return usernameField.isDisplayed();
    }

    public ResetPasswordLinkSentPage submitUsername(String email) throws InterruptedException {

        usernameField.sendKeys(email);

        gmailService.connect();

        findAccountButton.click();

        return new ResetPasswordLinkSentPage(driver);
    }

}
