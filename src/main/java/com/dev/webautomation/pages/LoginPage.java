package com.dev.webautomation.pages;

import com.dev.webautomation.factory.PlaywrightFactory;
import com.dev.webautomation.utils.CommonActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;

public class LoginPage extends PlaywrightFactory {
          public CommonActions commonActions;

        public LoginPage(Page page) {
            this.page = page;
            commonActions = new CommonActions(page);
        }

        // Page locators
        private Locator email_Txt = page.locator("#username");
        private Locator password_Txt =  page.locator("#password");
        private Locator login_Btn =  page.locator("#kc-login");
        private Locator remeberMe_Btn =  page.locator("#rememberMe");
        private Locator forgotPassword_Lbl =  page.locator("a:has-text('Forgot Password?')");
        private Locator errorMsg =  page.locator("#input-error");
        public Locator forgotPassword_Txt =  page.locator("#kc-page-title");
        public Locator backToLogin_Btn = page.locator("a:has-text('« Back to Login')");
        private Locator register_Btn =  page.locator("a:has-text('Register')");


        // To fill the login form using email and password and click on sign in button
        public void completeLoginForm(String email, String password) {
            commonActions.waitForSelector("#rememberMe");
            Assert.assertTrue(commonActions.checkElementIsDisplayed(remeberMe_Btn));
            Assert.assertTrue(commonActions.checkElementIsDisplayed(forgotPassword_Lbl));
            commonActions.clearFieldAndEnterText(email_Txt, email);
            commonActions.clearFieldAndEnterText(password_Txt, password);
            commonActions.click(login_Btn);
        }

        // To check error message in case of invalid login credentials.
        public void checkErrorMessageForInvalidLogin(String error) {
            Assert.assertTrue(commonActions.checkElementIsDisplayed(errorMsg));
//		Assert.assertTrue(commonActions.compareText(errorMsg, error));
        }

        // verify the forgot password page
        public void checkForgotPasswordPage() {
            commonActions.click(forgotPassword_Lbl);
            commonActions.waitForSelector("#kc-page-title");
            Assert.assertTrue(commonActions.checkElementIsDisplayed(forgotPassword_Txt));
        }

        // go back to login page
        public void goBackToLoginScreen() {
            commonActions.click(backToLogin_Btn);
        }

        // checked the remember me option
        public void clickOnRememberMe() {
            commonActions.click(remeberMe_Btn);
        }

        // logged out and check the email is showing or not
        public void checkEmailIdAfterLogout(String email) {
            String emailAfterLogout = commonActions.getText(email_Txt);
            Assert.assertFalse(emailAfterLogout.equalsIgnoreCase(email));
        }

        public void clickOnRegister() {
            commonActions.click(register_Btn);
        }

    }