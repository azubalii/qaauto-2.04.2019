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

    public EnterNewPasswordPage goToResetLink() throws InterruptedException {

        String messageSubject = "here's the link to reset your password";
        String messageTo = "auto.test.email02@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gmailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message + "\n\n");

        String resetLink = message.substring(message.indexOf("https://www.linkedin.com/e/v2"), message.indexOf("sig=")+14);
        resetLink = resetLink.replace("&amp;", "&");
        System.out.println("Reset link is: " + resetLink);
        driver.get(resetLink);

        sleep(2000);

        return new EnterNewPasswordPage(driver);
    }
}
