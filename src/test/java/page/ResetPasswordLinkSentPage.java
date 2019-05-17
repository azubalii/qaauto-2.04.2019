package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GmailService;

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

    public EnterNewPasswordPage navigateToLinkFromEmail() {
// extract sub-string from string in email response
        return new EnterNewPasswordPage(driver);
    }
}
