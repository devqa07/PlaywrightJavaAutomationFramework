package com.dev.webautomation.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.Random;

public class CommonActions {
    Page page;

    public CommonActions(Page page) {
        this.page = page;
    }

    public void click(Locator locator) {
        locator.click();
    }

    public void clearFieldAndEnterText(Locator locator, String value) {
        locator.clear();
        locator.fill(value);
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public String getURL() {
        return page.url();
    }

    public boolean checkElementIsDisplayed(Locator locator) {
        return locator.isVisible();
    }

    public void waitForSelector(String value) {
        page.waitForSelector(value);
    }
    public boolean compareText(Locator locator, String message) {
        return locator.textContent().equalsIgnoreCase(message.trim());
    }

    public String getText(Locator locator) {
        return locator.textContent();
    }

    public String randomEmailGenerator() {
        String name = "testmail2345689008";
        StringBuilder nameBuild = new StringBuilder();
        Random rnd = new Random();
        while (nameBuild.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * name.length());
            nameBuild.append(name.charAt(index));
        }
        String saltStr = nameBuild.toString();
        saltStr = saltStr + "@gmail.com";
        return saltStr;
    }
}