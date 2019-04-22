import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WelcomeBackPage {
    private WebDriver driver;
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;
    private WebElement errorForPassword;

    public WelcomeBackPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        emailField = driver.findElement(By.xpath("//input[@id='username']"));
        passwordField = driver.findElement(By.xpath("//input[@id='password']"));
        signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        errorForPassword = driver.findElement(By.xpath("//div[@id='error-for-password']"));
    }

    public String getErrorForPasswordText() {
        return errorForPassword.getText();
    }

    public String getPasswordFieldAreaDescribedByAttribute() {
        return passwordField.getAttribute("aria-describedby");
    }
}
