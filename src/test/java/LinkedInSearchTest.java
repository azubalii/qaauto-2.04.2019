import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedInSearchTest extends BaseTest {

    @BeforeMethod
    public void beforeMethodTwo (){
        System.out.println("2nd before method");
    }

    @Test
    public void searchOnHomePageTest() {
        String userEmail = "auto.test.email01@gmail.com";
        String userPassword = "linked123";
        String searchTerm = "HR";

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded.");

        SearchResultPage searchResultPage = homePage.search(searchTerm);
        Assert.assertTrue(searchResultPage.isPageLoaded(), "Search page is not loaded.");
        Assert.assertEquals(searchResultPage.searchResultsSize(), 10, "Number of search results is not 10");

        searchResultPage.scrollThePage();
        searchResultPage.sleepTime(1000);
        Assert.assertTrue(searchResultPage.isSearchRequestPresentInResult(searchTerm), "Search term \"" + searchTerm + "\" not found");
    }
}
