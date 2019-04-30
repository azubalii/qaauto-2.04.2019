import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sun.awt.SunHints;

import javax.naming.directory.SearchResult;

import java.security.Key;

import static java.lang.Thread.sleep;

public class HomePage {
    private WebDriver driver;

    private WebElement profileMenuItem;
    private WebElement searchField;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        searchField = driver.findElement(By.xpath("//artdeco-typeahead-deprecated[@id='nav-search-artdeco-typeahead']//input"));
    }

    // is get set
    public void clickOnProfileMenuItem() {
        profileMenuItem.click();
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && driver.getTitle().equals("LinkedIn")
                && profileMenuItem.isDisplayed();
    }

    public boolean isProfileUserNameCorrect() {
        return driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']//h3"))
                .getText()
                .equals("Kian Miller");
    }

    public SearchResultPage search(String searchRequest) {
        searchField.sendKeys(searchRequest, Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SearchResultPage(driver);
    }
}
