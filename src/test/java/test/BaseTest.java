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
    @Parameters({"browserName", "language"})
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String browserName, String language) throws Exception {
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

        String baseLink = "https://www.linkedin.com";

        if (language.toLowerCase().equals("en")){
            driver.get(baseLink);
        } else if (language.toLowerCase().equals("de")){
            baseLink = "https://de.linkedin.com";
            driver.get(baseLink);
        } else if (language.toLowerCase().equals("ua")){
            baseLink = "https://ua.linkedin.com";
            driver.get(baseLink);
        } else {
            System.out.println("Localization is not defined. \nRunning with default link.");
            driver.get(baseLink);
        }

        loginPage = new LoginPage(driver);
        System.out.println("1st before method");
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
