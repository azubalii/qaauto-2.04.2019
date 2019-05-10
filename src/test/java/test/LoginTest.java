package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.WelcomeBackPage;

public class LoginTest extends BaseTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"auto.test.email01@gmail.com", "linked123"},
                {"Auto.Test.Email01@gmail.com", "linked123"},
                {" Auto.Test.Email01@gmail.com", "linked123"}
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Homepage is not loaded.");

        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Kian Miller", "User name is not correct");
    }

    @DataProvider
    public Object[][] emptyDataProvider() {
        return new Object[][]{
                {"auto.test.email01@gmail.com", ""},
                {"", "linked123"},
                {"", ""}
        };
    }

    @Test(dataProvider = "emptyDataProvider")
    public void negativeLoginTestEmptyCredentials(String userEmail, String userPassword) {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                {"auto.test.email01@gmail.com", "_linked123", "", "Hmm, that's not the right password. Please try again or request a new one."},
                {"_Auto.Test.Email01@gmail.com", "linked123", "Hmm, we don't recognize that email. Please try again.", ""},
                {"_Auto.Test.Email01@gmail.com", "_linked123", "Hmm, we don't recognize that email. Please try again.", ""}
        };
    }

    @Test(dataProvider = "invalidDataProvider")
    public void negativeLoginTestWrongCredentials(String userEmail,
                                                  String userPassword,
                                                  String userEmailValidationMessage,
                                                  String userPasswordValidationMessage) {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        WelcomeBackPage welcomeBackPage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(welcomeBackPage.isPageLoaded(), "Welcome page is not loaded.");

        Assert.assertEquals(welcomeBackPage.getUserEmailValidationMessage(), userEmailValidationMessage, "Wrong validation message on user email");
        Assert.assertEquals(welcomeBackPage.getUserPasswordValidationMessage(), userPasswordValidationMessage, "Wrong validation message on user password");
    }
}
