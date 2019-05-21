package page;

import org.openqa.selenium.WebDriver;
import util.GmailService;

public abstract class BasePage {
    protected WebDriver driver;
    public static GmailService gmailService = new GmailService();

    public abstract boolean isPageLoaded();
}
