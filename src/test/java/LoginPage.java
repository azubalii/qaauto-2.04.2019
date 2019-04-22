import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    WebElement emailField;
    WebElement passwordField;
    WebElement signInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        emailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        passwordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
    }

    public void login(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTitleText() {
        return driver.getTitle();
    }

    public String getEmailFieldDirAttribute() {
        return emailField.getAttribute("dir");
    }

    public Boolean isSinginButtonDisplayed() {
        return signInButton.isDisplayed();
    }
}
