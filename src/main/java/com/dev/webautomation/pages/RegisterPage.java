package com.dev.webautomation.pages;

import com.dev.webautomation.factory.PlaywrightFactory;
import com.dev.webautomation.utils.CommonActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;

public class RegisterPage extends PlaywrightFactory {
    public CommonActions commonActions;

    public RegisterPage(Page page) {
        this.page = page;
        commonActions = new CommonActions(page);
    }

    // Page locators
    private Locator email_Txt = page.locator("#email");
    private Locator password_Txt = page.locator("#password");
    private Locator passwordConfirm_Txt = page.locator("#password-confirm");
    private Locator register_Btn = page.locator("input:has-text('Register')");
    private Locator register_Lbl = page.locator("#kc-form-buttons");
    private Locator errorMsg = page.locator("span:right-of(span)");
    private Locator backToLogin_Btn = page.locator("a:has-text('« Back to Login')");

    // To fill the login form using email and password and click on sign in button
    public void completeRegisterForm(String email, String password, String confirmPassword) {
        commonActions.waitForSelector("#kc-form-buttons");
        Assert.assertTrue(commonActions.checkElementIsDisplayed(register_Lbl));
        Assert.assertTrue(commonActions.checkElementIsDisplayed(backToLogin_Btn));
        commonActions.clearFieldAndEnterText(email_Txt, email);
        commonActions.clearFieldAndEnterText(password_Txt, password);
        commonActions.clearFieldAndEnterText(passwordConfirm_Txt, confirmPassword);
        commonActions.click(register_Btn);
    }

    // To check error message in case of invalid login credentials.
    public void checkErrorMessageForInvalidRegister(String error) {
        Assert.assertTrue(commonActions.checkElementIsDisplayed(errorMsg));
        Assert.assertTrue(commonActions.compareText(errorMsg, error));
    }
    public void checkAndClickOnBackButton() {
        Assert.assertTrue(commonActions.checkElementIsDisplayed(backToLogin_Btn));
        commonActions.click(backToLogin_Btn);
    }
}