package com.saucedemo.tests;

import com.saucedemo.Endpoints;
import com.saucedemo.MobileTestsBase;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.TimeoutException;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Real Device Web Tests.
 */
@RunWith(Parameterized.class)
public class RealDeviceWebTests extends MobileTestsBase {
    @Parameterized.Parameter
    public String deviceName;

    @Parameterized.Parameters()
    public static Collection<Object[]> iosConfigurations() {
        return Arrays.asList(new Object[][]{
                {"iPhone.*"},
                // {"iPhone 12.*"},
                // {"iPad 10.*"},
                // {"iPad Air.*"},
                // {"iPad.*"},
                // Duplication below for demo purposes of massive parallelization
        });
    }

    @Before
    public void setUp() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("platformName", "iOS");        
        capabilities.setCapability("appium:deviceName", deviceName);
        capabilities.setCapability("appium:automationName", "XCUITest");
        /*
        * if you set the browserName => always starts with webcontext
            if you set the app => always starts with native context
            if you set the package/bundleId => always starts with native context
            if you have a hybrid app and set autoWebview  => always starts with webview
        * */
        capabilities.setCapability("browserName", "Safari");
    
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", EmuSimWebAppTests.SAUCE_USERNAME);
        sauceOptions.setCapability("accesskey", EmuSimWebAppTests.SAUCE_ACCESS_KEY);
        sauceOptions.setCapability("build", BUILD_NAME);
        sauceOptions.setCapability("name", testName.getMethodName());
        capabilities.setCapability("sauce:options", sauceOptions);

        driver = new IOSDriver(Endpoints.getSauceHub(), capabilities);
    }

    @Test
    public void loginWorks() {
        for(int i = 0; i < 40; i++){
            LoginPage loginPage = new LoginPage(getDriver());
            loginPage.visit();
            loginPage.login("standard_user");
            assertTrue(new ProductsPage(driver).isDisplayed());
        }
    }

    @Test(expected = TimeoutException.class)
    public void lockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.login("locked_out_user");
        assertFalse(new ProductsPage(driver).isDisplayed());
    }

    @Test(expected = TimeoutException.class)
    public void invalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.login("foo_bar_user");
        assertFalse(new ProductsPage(driver).isDisplayed());
    }
}
