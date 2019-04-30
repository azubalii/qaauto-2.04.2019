import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private WebElement searchElement;
    private List<WebElement> searchResults;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        searchElement = driver.findElement(By.xpath("//li[contains(@class,'search-result search-result__occluded-item')]"));
        searchResults = driver.findElements(By.xpath("//li[contains(@class,'search-result search-result__occluded-item')]"));
    }

    public boolean isPageLoaded() {
        return searchElement.isDisplayed();
    }

    public int searchResultsSize() {
        return searchResults.size();
    }

    public void scrollThePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2000)");
    }

    public boolean isSearchRequestPresentInResult() {
        boolean result = true;
        for (WebElement searchResult : searchResults) {
            if (!searchResult.getText().contains("HR")) {
                result = false;
            }
        }
        return result;
    }
}
