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
        Assert.assertEquals(emailField.getAttribute("placeholder"),"Email");
        Assert.assertEquals(passwordField.getAttribute("placeholder"),"Password");
        Assert.assertEquals(signInButton.getAttribute("value"),"Sign in");
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();

    }
}
