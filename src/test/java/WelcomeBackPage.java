import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WelcomeBackPage {
    private WebDriver driver;
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;
    private WebElement userEmailValidationMessage;
    private WebElement userPasswordValidationMessage;

    public WelcomeBackPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        emailField = driver.findElement(By.xpath("//input[@id='username']"));
        passwordField = driver.findElement(By.xpath("//input[@id='password']"));
        signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        userEmailValidationMessage = driver.findElement((By.xpath("//div[@id='error-for-username']")));
        userPasswordValidationMessage = driver.findElement((By.xpath("//div[@id='error-for-password']")));
    }

    public boolean isPageLoaded() {
        return passwordField.isDisplayed()
                && emailField.isDisplayed()
                && signInButton.isDisplayed();
    }

    public String getUserEmailValidationMessage() {
        return userEmailValidationMessage.getText();
    }

    public String getUserPasswordValidationMessage() {
        return userPasswordValidationMessage.getText();
    }
}
