package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class ResetPasswordTest extends BaseTest {

    @Test
    public void resetPasswordTest() throws InterruptedException {
        String email = "auto.test.email02@gmail.com";
        String password2 = "linked!@#$%12";

        ResetPasswordPage resetPasswordPage = loginPage.clickOnResetPasswordLink();
        Assert.assertTrue(resetPasswordPage.isPageLoaded(), "Reset Password page is not loaded.");

        ResetPasswordLinkSentPage resetPasswordLinkSentPage = resetPasswordPage.submitUsername(email);
        Assert.assertTrue(resetPasswordLinkSentPage.isPageLoaded(), "Reset Password Link Sent page is not loaded.");

        EnterNewPasswordPage enterNewPasswordPage = resetPasswordLinkSentPage.goToResetLink(email);
        Assert.assertTrue(enterNewPasswordPage.isPageLoaded(), "Page is not loaded.");

        ResetPasswordSuccessPage resetPasswordSuccessPage = enterNewPasswordPage.submitNewPassword(password2);
        Assert.assertTrue(resetPasswordSuccessPage.isPageLoaded(), "Page is not loaded.");

        HomePage homePage = resetPasswordSuccessPage.goToHomepage();
        Assert.assertTrue(homePage.isPageLoaded(), "Page is not loaded.");
    }
}
