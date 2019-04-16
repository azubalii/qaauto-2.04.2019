import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void successfulLoginTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String linkedInUrl = "https://www.linkedin.com";
        String userEmail = "auto.test.email01@gmail.com";
        String userPassword = "linked123";

        driver.get(linkedInUrl);

        WebElement emailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        Assert.assertEquals(emailField.getAttribute("placeholder"), "Email");
        Assert.assertEquals(passwordField.getAttribute("placeholder"), "Password");
        Assert.assertEquals(signInButton.getAttribute("value"), "Sign in");
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        Assert.assertTrue(profileMenuItem.isDisplayed(), "Homepage is not loaded.");
        profileMenuItem.click();

        WebElement profileUserName = driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']//h3"));
        Assert.assertEquals(profileUserName.getText(), "Kian Miller", "Wrong profile user name displayed.");

        driver.quit();
    }

    @Test
    public void negativeLoginTestEmptyCredentials() {

    }

    @Test
    public void negativeLoginTestWrongCredentials() {

    }
}
