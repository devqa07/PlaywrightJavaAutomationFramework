package com.dev.tests.base;

import com.dev.webautomation.factory.PlaywrightFactory;
import com.dev.webautomation.pages.HomePage;
import com.dev.webautomation.pages.LoginPage;
import com.dev.webautomation.pages.RegisterPage;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    PlaywrightFactory pf;
    Page page;
    protected Properties prop;

    protected HomePage homePage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;

    @Parameters({ "browser" })
    @BeforeTest
    public void setup(String browserName) throws IOException {
        pf = new PlaywrightFactory();

        prop = pf.loadConfig();

        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }

        page = pf.initBrowser();
        //homePage = new HomePage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }

}

