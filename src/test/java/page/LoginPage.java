package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;
    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        if (driver.getCurrentUrl().equals("https://www.linkedin.com/")) {
            System.out.println("Base page is displayed");
            return driver.getTitle().contains("LinkedIn: Log In or Sign Up")
                    && signInButton.isDisplayed();
        } else if (driver.getCurrentUrl().equals("https://ua.linkedin.com/")) {
            System.out.println("Ukraine page is displayed");
            return driver.getTitle().contains("LinkedIn: Log In or Sign Up")
                    && signInButton.isDisplayed();
        } else if (driver.getCurrentUrl().equals("https://de.linkedin.com/")) {
            System.out.println("Deutsche page is displayed");
            return driver.getTitle().contains("LinkedIn: Einloggen oder Registrieren")
                    && signInButton.isDisplayed();
        } else {
            return false;
        }
    }

    public <GenericPage> GenericPage login(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        if (driver.getCurrentUrl().contains("/feed")) {
            return (GenericPage) new HomePage(driver);
        }
        if (driver.getCurrentUrl().contains("/uas/login-submit")) {
            return (GenericPage) new WelcomeBackPage(driver);
        } else {
            return (GenericPage) new LoginPage(driver);
        }
    }

    public ResetPasswordPage clickOnResetPasswordLink() {
        forgotPasswordButton.click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResetPasswordPage(driver);
    }
}
