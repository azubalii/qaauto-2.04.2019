import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class HomePage {
    private WebDriver driver;
    private WebElement profileMenuItem;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }

    // is get set
    public void clickOnProfileMenuItem() {
        profileMenuItem.click();
    }

    public Boolean isHomePageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && driver.getTitle().equals("LinkedIn")
                && profileMenuItem.isDisplayed();
    }

    public boolean isProfileUserNameCorrect() {
        return driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']//h3"))
                .getText()
                .equals("Kian Miller");
    }
}
