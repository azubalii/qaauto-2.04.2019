import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String searchText = "Selenium";

        driver.get("https://www.google.com/");
        //driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("Selenium",Keys.ENTER);
        //driver.findElement(By.className("gLFyf gsfi"));
        //driver.findElement(By.cssSelector("input.gLFyf.gsfi"));

        WebElement searchField = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
        System.out.println(searchField.getText());
        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.ENTER);

        WebElement navigationButton8 = driver.findElement(By.xpath("//td/a[contains(text(),'8')]"));
        navigationButton8.click();

        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println("Search results on page 8 count: " + searchResults.size() + " items in total\n");

        int found = 0;
        int notFound = 0;
        for (int i = 1; i <= searchResults.size(); i++) {
            WebElement searchResultTitle = driver.findElement(By.xpath("//div[@class='srg']/div[@class='g'][" + i + "]//h3"));

            if (searchResultTitle.getText().contains(searchText)) {
                System.out.println("In item #" + i + " search term was found:");
                found++;
            } else {
                System.out.println("In item #" + i + " search term was not found:");
                notFound++;
            }
            System.out.println("\"" + searchResultTitle.getText() + "\"\n");

        }
        System.out.println("Total found = " + found);
        System.out.println("Total not found = " + notFound);

        for (WebElement searchResult : searchResults) {
            String searchResultText = searchResult.getText();
            if (searchResultText.toLowerCase().contains(searchText.toLowerCase())) {
                System.out.println("Search term " + searchText + " found in : \n" + searchResultText + "\n\n");
            } else {
                System.out.println("Search term " + searchText + " NOT found in : \n" + searchResultText + "\n\n");
            }
        }

    }
}
