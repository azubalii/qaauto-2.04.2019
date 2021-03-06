package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class HomePage extends BasePage {

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileMenuItem;
    @FindBy(xpath = "//form[@id='extended-nav-search']//input")
    private WebElement searchField;
    @FindBy(xpath = "//ul[@id='nav-settings__dropdown-options']//h3")
    private WebElement profileUserName;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnProfileMenuItem() {
        profileMenuItem.click();
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && driver.getTitle().equals("LinkedIn")
                && profileMenuItem.isDisplayed();
    }

    public String getProfileUserNameText() {
        return profileUserName.getText();
    }

    public SearchResultPage search(String searchTerm) {
        searchField.sendKeys(searchTerm, Keys.ENTER);
        return new SearchResultPage(driver);
    }
}
