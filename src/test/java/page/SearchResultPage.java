package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {
    @FindBy(xpath = "//div[@class='search-results-page core-rail']")
    private WebElement searchResultsContainer;
    @FindBy(xpath = "//li[contains(@class,'search-result search-result__occluded-item')]")
    private List<WebElement> searchResultElements;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return searchResultsContainer.isDisplayed();
    }

    public int getSearchResultsSize() {
        return searchResultElements.size();
    }
/*
    public void sleepTime(int time) {
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollThePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2000)");
    }

    public boolean isSearchRequestPresentInResult(String searchRequest) {
        for (WebElement searchResultElement : searchResultElements) {
            if (!searchResultElement.getText().contains(searchRequest)) {
                return false;
            }
        }
        return true;
    }
*/
    public List<String> getSearchResultsTest() {
        List<String> searchResultsList = new ArrayList<String>();
        for (WebElement searchResultElement : searchResultElements){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchResultElement);
            String searchResultText = searchResultElement.getText();
            searchResultsList.add(searchResultText);
        }
        return searchResultsList;
    }
}
