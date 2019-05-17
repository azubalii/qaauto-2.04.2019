package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GmailService;

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

    public ResetPasswordLinkSentPage submitUsername(String email) {
        usernameField.sendKeys(email);

        String messageSubject = "the link to reset your password";
        String messageTo = email;
        String messageFrom = "security-noreply@linkedin.com";

        GmailService gMailService = new GmailService();
        gMailService.connect();
        findAccountButton.click();
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message);


        return new ResetPasswordLinkSentPage(driver);
    }

}
