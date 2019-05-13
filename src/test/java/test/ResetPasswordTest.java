package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class ResetPasswordTest extends BaseTest{
    //getLinkFromEmail


    @Test
    public void resetPasswordTest (){
        String email = "auto.test.email02@gmail.com";
        String password1 = "gmail+123";
        String password2 = "linked+1234";

        ResetPasswordPage resetPasswordPage = loginPage.resetPassword();
        Assert.assertTrue(resetPasswordPage.isPageLoaded(), "Reset Password page is not loaded.");

        ResetPasswordLinkSentPage resetPasswordLinkSentPage = resetPasswordPage.submitUsername(email);
        Assert.assertTrue(resetPasswordLinkSentPage.isPageLoaded(), "Reset Password Link Sent page is not loaded.");

        GmailLoginPage gmailLoginPage = resetPasswordLinkSentPage.redirectToGmailPage();
        Assert.assertTrue(gmailLoginPage.isPageLoaded(), "Page is not loaded.");

        gmailLoginPage.submitEmail(email);
        GmailPage gmailPage = gmailLoginPage.submitPassword(password1);
        Assert.assertTrue(gmailPage.isPageLoaded(), "Page is not loaded.");

        EnterNewPasswordPage enterNewPasswordPage = gmailPage.goToResetLink();
        Assert.assertTrue(enterNewPasswordPage.isPageLoaded(), "Page is not loaded.");

        ResetPasswordSuccessPage resetPasswordSuccessPage = enterNewPasswordPage.submitNewPassword(password2);
        Assert.assertTrue(resetPasswordSuccessPage.isPageLoaded(), "Page is not loaded.");

        HomePage homePage = resetPasswordSuccessPage.goToHomepage();
        Assert.assertTrue(homePage.isPageLoaded(), "Page is not loaded.");



    }
}
