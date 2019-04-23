import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"auto.test.email01@gmail.com", "linked123"},
                {"Auto.Test.Email01@gmail.com", "linked123"},
                {" Auto.Test.Email01@gmail.com", "linked123"}
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

    @DataProvider
    public Object[][] emptyDataProvider() {
        return new Object[][]{
                {"auto.test.email01@gmail.com", ""},
                {"", "linked123"},
                {"", ""}
        };
    }

    @Test(dataProvider = "emptyDataProvider")
    public void negativeLoginTestEmptyCredentials(String userEmail, String userPassword) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);

        Assert.assertTrue(loginPage.isSingInButtonDisplayed(), "LogIn page is not displayed.");
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is displayed");

        driver.quit();
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                {"auto.test.email01@gmail.com", "_linked123"},
                {"_Auto.Test.Email01@gmail.com", "linked123"},
                {"_Auto.Test.Email01@gmail.com", "_linked123"}
        };
    }

    @Test(dataProvider = "invalidDataProvider")
    public void negativeLoginTestWrongCredentials(String userEmail, String userPassword) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);

        WelcomeBackPage welcomeBackPage = new WelcomeBackPage(driver);

        Assert.assertTrue(welcomeBackPage.isWelcomePageDisplayed(), "Welcome page is not displayed");
        driver.quit();
    }
}
