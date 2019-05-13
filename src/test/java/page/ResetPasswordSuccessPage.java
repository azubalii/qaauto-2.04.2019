package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class ResetPasswordSuccessPage extends BasePage {
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement goToHomepageButton;

    public ResetPasswordSuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return goToHomepageButton.isDisplayed();
    }

    public HomePage goToHomepage(){
        goToHomepageButton.click();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HomePage(driver);
    }
}
