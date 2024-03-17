package com.dev.tests.tests;

import com.codoid.products.exception.FilloException;
import com.dev.webautomation.factory.PlaywrightFactory;
import com.dev.webautomation.listeners.ExtentReportListener;
import com.dev.webautomation.pages.HomePage;
import com.dev.webautomation.pages.LoginPage;
import com.dev.webautomation.pages.RegisterPage;
import com.dev.webautomation.utils.CommonActions;
import com.dev.webautomation.utils.ExcelUtils;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

@Listeners(ExtentReportListener.class)
public class RegisterTest extends PlaywrightFactory {

    public CommonActions commonActions;
    LoginPage loginPageElements;
    HomePage homePageElements;
    RegisterPage registerPageElements;
    public Page page;
    private Logger log = LogManager.getLogger(LoginPage.class.getName());
    private HashMap<String, String> data;


    @BeforeMethod
    public void initialize() throws IOException {
        page= initBrowser();
        commonActions = new CommonActions(page);
        loginPageElements = new LoginPage(page);
        homePageElements = new HomePage(page);
        registerPageElements=new RegisterPage(page);
        System.out.println(prop.getProperty("url"));
        commonActions.navigateTo(prop.getProperty("url"));
    }


    @Test(priority = 0, description = "This method is to check the valid scenarios for register page")
    public void performRegistrationWithValidDetails() throws IOException, FilloException {
        // get data from excel sheet for TC5
        data = new ExcelUtils().getTestData("register", "TC6");
        // enter valid email, password and confirm password and click on register
        loginPageElements.clickOnRegister();
        registerPageElements.completeRegisterForm(commonActions.randomEmailGenerator(), data.get("Password"),
                data.get("ConfirmPassword"));
        // verify homepage url and check home page is displayed.
        homePageElements.checkHomePageAfterLogin(data.get("url"));
        log.info("Successfully Registered");
    }

    @Test(priority = 1, description = "This method is to check all the invalid scenarios for register page")
    public void performRegistrationWithInvalidDetails() throws IOException, FilloException {
        // get data from excel sheet for TC1
        data = new ExcelUtils().getTestData("register", "TC1");
        // enter invalid email and password and click on register
        loginPageElements.clickOnRegister();
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC1");
        // get data from excel sheet for TC2
        data = new ExcelUtils().getTestData("register", "TC2");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC2");
        // get data from excel sheet for TC3
        data = new ExcelUtils().getTestData("register", "TC3");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC3");
        // get data from excel sheet for TC4
        data = new ExcelUtils().getTestData("register", "TC4");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC4");
        // get data from excel sheet for TC4
        data = new ExcelUtils().getTestData("register", "TC5");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC5");
    }

    @Test(priority = 2, description = "Check back button on register page")
    public void verifyBackButtonOnRegister() {
        loginPageElements.clickOnRegister();
        registerPageElements.checkAndClickOnBackButton();
        Assert.assertTrue(commonActions.checkElementIsDisplayed(loginPageElements.forgotPassword_Txt));


    }
}