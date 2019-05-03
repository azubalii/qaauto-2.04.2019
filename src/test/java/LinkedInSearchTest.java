import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedInSearchTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        loginPage = new LoginPage(driver);
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"auto.test.email01@gmail.com", "linked123", "HR"}
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void searchOnHomePageTest(String userEmail, String userPassword, String searchRequest) {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded.");

        SearchResultPage searchResultPage = homePage.search(searchRequest);
        Assert.assertTrue(searchResultPage.isPageLoaded(), "Search page is not loaded.");
        Assert.assertEquals(searchResultPage.searchResultsSize(), 10, "Number of search results is not 10");

        searchResultPage.scrollThePage();
        searchResultPage.sleepTime(1000);
        Assert.assertTrue(searchResultPage.isSearchRequestPresentInResult(searchRequest), "Search term \"" + searchRequest + "\" not found");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
