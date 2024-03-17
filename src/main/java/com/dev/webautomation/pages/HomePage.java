package com.dev.webautomation.pages;

import com.dev.webautomation.factory.PlaywrightFactory;
import com.dev.webautomation.utils.CommonActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;

public class HomePage extends PlaywrightFactory{
    public CommonActions commonActions;

    public HomePage(Page page) {
        this.page = page;
        commonActions = new CommonActions(page);
    }
    // page locators
    private Locator topUp_Lbl = page.locator("button:has-text(\"Top Up\")");
    private Locator logout_Btn = page.locator("button:has-text('Logout')");
    private Locator header_Lbl = page.locator("//span[text()=\"S\"]");

    // To check Home page is visible after login
    public void checkHomePageAfterLogin(String url) {
        commonActions.waitForSelector("button:has-text(\"Top Up\")");
        Assert.assertTrue(commonActions.getURL().equals(url));
        Assert.assertTrue(commonActions.checkElementIsDisplayed(topUp_Lbl));
    }

    // Open Profile and click on logout button on Home page
    public void performLogout() {
        commonActions.click(header_Lbl);
        commonActions.click(logout_Btn);
    }

}

