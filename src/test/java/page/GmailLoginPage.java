package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class GmailLoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='identifierId']")
    private WebElement identifierField;
    @FindBy(xpath = "//div[@id='identifierNext']")
    private WebElement identifierNextButton;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//div[@id='passwordNext']")
    private WebElement passwordNextButton;

    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return identifierField.isDisplayed();
    }

    public void submitEmail(String email) {
        identifierField.sendKeys(email);
        identifierNextButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public GmailPage submitPassword(String password) {
        passwordField.sendKeys(password);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        passwordNextButton.click();
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new GmailPage(driver);
    }
}
