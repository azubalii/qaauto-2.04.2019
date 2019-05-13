package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class ResetPasswordLinkSentPage extends BasePage {

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    public ResetPasswordLinkSentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isPageLoaded() {
        return resendLinkButton.isDisplayed();
    }

    public GmailLoginPage redirectToGmailPage() {
        driver.get("https://mail.google.com/mail/#inbox");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new GmailLoginPage(driver);
    }
}
