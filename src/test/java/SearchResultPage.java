import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;

public class SearchResultPage {
    private WebDriver driver;
    private WebElement searchResultsBlock;
    private List<WebElement> searchResultsElements;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        searchResultsBlock = driver.findElement(By.xpath("//div[@class='search-results-page core-rail']"));
        searchResultsElements = driver.findElements(By.xpath("//li[contains(@class,'search-result search-result__occluded-item')]"));
    }

    public boolean isPageLoaded() {
        return searchResultsBlock.isDisplayed();
    }

    public int searchResultsSize() {
        return searchResultsElements.size();
    }

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
        boolean result = true;
        for (WebElement searchResultElement : searchResultsElements) {
            if (!searchResultElement.getText().contains(searchRequest)) {
                result = false;
                sleepTime(5000);
            }
        }
        return result;
    }
}
