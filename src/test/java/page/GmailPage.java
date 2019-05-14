package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class GmailPage extends BasePage {

    @FindBy(xpath = "//tr[@class='zA zE']")
    private WebElement inboxMail;
    @FindBy(xpath = "//a[contains(text(), 'Reset my password')]")
    private WebElement resetPasswordLink;

    public GmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return inboxMail.isDisplayed();
    }

    public EnterNewPasswordPage goToResetLink() {
        inboxMail.click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String resetPasswordHref = resetPasswordLink.getAttribute("data-saferedirecturl");
        driver.get(resetPasswordHref);
        //resetPasswordLink.click();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new EnterNewPasswordPage(driver);
    }
}
