import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "auto.test.email01@gmail.com", "linked123" },
                { "Auto.Test.Email01@gmail.com", "linked123" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);

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
        driver.get("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("auto.test.email01@gmail.com", "");

        Assert.assertEquals(loginPage.getEmailFieldDirAttribute(), "ltr", "Email field is blank.");
        Assert.assertTrue(loginPage.isSingInButtonDisplayed(), "LogIn page is not displayed.");
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is displayed");

        driver.quit();
    }

    @Test
    public void negativeLoginTestWrongPassword() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("auto.test.email01@gmail.com", "_linked123");

        WelcomeBackPage welcomeBackPage = new WelcomeBackPage(driver);
        Assert.assertEquals(welcomeBackPage.getErrorForPasswordText(), "Hmm, that's not the right password. Please try again or request a new one.", "LogIn error text is not correct");
        Assert.assertEquals(welcomeBackPage.getPasswordFieldAreaDescribedByAttribute(), "error-for-password", "Password field is not highlighted");

        driver.quit();
    }
}
