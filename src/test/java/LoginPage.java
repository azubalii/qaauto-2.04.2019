import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class LoginPage {
    private WebDriver driver;

    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        emailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        passwordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
    }

    public HomePage loginToHomePage(String userEmail, String userPassword) {
        login(userEmail, userPassword);
        return new HomePage(driver);
    }

    public void loginToLoginPage(String userEmail, String userPassword) {
        login(userEmail, userPassword);
    }

    public WelcomeBackPage loginToWelcomePage(String userEmail, String userPassword) {
        login(userEmail, userPassword);
        return new WelcomeBackPage(driver);
    }

    public void login(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/")
                && driver.getTitle().contains("LinkedIn: Log In or Sign Up")
                && signInButton.isDisplayed();
    }
}
