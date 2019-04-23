import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WelcomeBackPage {
    private WebDriver driver;
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;

    public WelcomeBackPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        emailField = driver.findElement(By.xpath("//input[@id='username']"));
        passwordField = driver.findElement(By.xpath("//input[@id='password']"));
        signInButton = driver.findElement(By.xpath("//button[@type='submit']"));

    }

    public boolean isWelcomePageDisplayed() {
        return passwordField.isDisplayed()
                && emailField.isDisplayed()
                && signInButton.isDisplayed();
    }
}
