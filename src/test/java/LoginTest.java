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
        driver.get("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("auto.test.email01@gmail.com", "linked123");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed(), "Homepage is not loaded.");

        homePage.clickOnProfileMenuItem();

        Assert.assertEquals(homePage.getProfileUserNameText(), "Kian Miller", "Wrong profile user name displayed.");

        driver.quit();
    }

    @Test
    public void negativeLoginTestEmptyCredentials() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String linkedInUrl = "https://www.linkedin.com";
        String userEmail = "auto.test.email01@gmail.com";
        String userPassword = "";

        driver.get(linkedInUrl);

        WebElement emailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ", "LogIn page is not loaded.");
        Assert.assertEquals(emailField.getAttribute("placeholder"), "Email", "Placeholder is not correct in Email field");
        Assert.assertEquals(passwordField.getAttribute("placeholder"), "Password", "Placeholder is not correct in Password field");
        Assert.assertEquals(signInButton.getAttribute("value"), "Sign in", "SignIn button text is not correct");
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();

        Assert.assertEquals(emailField.getAttribute("dir"), "ltr", "Email field is blank.");
        Assert.assertTrue(signInButton.isDisplayed(), "LogIn page is not displayed.");

        driver.quit();
    }

    @Test
    public void negativeLoginTestWrongPassword() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String linkedInUrl = "https://www.linkedin.com";
        String userEmail = "auto.test.email01@gmail.com";
        String userPassword = "_linked123";

        driver.get(linkedInUrl);

        WebElement emailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ", "LogIn page is not loaded.");
        Assert.assertEquals(emailField.getAttribute("placeholder"), "Email", "Placeholder is not correct in Email field");
        Assert.assertEquals(passwordField.getAttribute("placeholder"), "Password", "Placeholder is not correct in Password field");
        Assert.assertEquals(signInButton.getAttribute("value"), "Sign in", "SignIn button text is not correct");
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();

        WebElement loginWrongPasswordErrorMessage = driver.findElement(By.xpath("//div[@id='error-for-password']"));
        WebElement passwordFieldOnError = driver.findElement(By.xpath("//input[@id='password']"));
        Assert.assertEquals(loginWrongPasswordErrorMessage.getText(), "Hmm, that's not the right password. Please try again or request a new one.", "LogIn error text is not correct");
        Assert.assertEquals(passwordFieldOnError.getAttribute("aria-describedby"), "error-for-password", "Password field is not highlighted");

        driver.quit();
    }
}
