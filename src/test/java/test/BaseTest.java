package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LoginPage;

public class BaseTest {
    private WebDriver driver;
    protected LoginPage loginPage;

    //TODO find out why not @BeforeTest
    @Parameters("browserName")
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String browserName) throws Exception {
        if (browserName.toLowerCase().equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.toLowerCase().equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new Exception("Unsupported 'browserName'");
        }

        driver.manage().window().maximize();
        driver.get("https://www.linkedin.com");
        loginPage = new LoginPage(driver);
        System.out.println("1st before method");
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
