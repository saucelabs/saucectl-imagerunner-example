package com.saucedemo.tests;

import com.saucedemo.Endpoints;
import com.saucedemo.MobileTestsBase;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Emulator / Simulator Web Tests.
 */
@RunWith(Parameterized.class)
public class EmuSimWebAppTests extends MobileTestsBase {

    /*
     * Configure our data driven parameters
     * */
    @Parameterized.Parameter
    public String browserName;
    @Parameterized.Parameter(1)
    public String platform;
    @Parameterized.Parameter(2)
    public String platformVersion;
    @Parameterized.Parameter(3)
    public String deviceName;

    @Parameterized.Parameters()
    public static Collection<Object[]> crossBrowserData() {
        return Arrays.asList(new Object[][]{
                {"Safari", "iOS", "15.4", "iPhone Simulator"},
                {"Safari", "iOS", "previous_major", "iPhone Fast Simulator"},
                // {"Safari", "iOS", "14.3", "iPhone XS Simulator"},
                // {"Safari", "iOS", "14.3", "iPhone SE (2nd generation) Simulator"}
                // Duplication below for demo purposes of massive parallelization
//                {"Safari", "iOS", "14.3", "iPhone XS Max Simulator"},
//                {"Safari", "iOS", "14.3", "iPhone XS Simulator"},
//                {"Safari", "iOS", "14.3", "iPhone SE (2nd generation) Simulator"},
//                {"Safari", "iOS", "14.3", "iPhone XS Max Simulator"},
//                {"Safari", "iOS", "14.3", "iPhone XS Simulator"},
//                {"Safari", "iOS", "14.3", "iPhone SE (2nd generation) Simulator"},
//                {"Safari", "iOS", "14.3", "iPhone XS Max Simulator"},
//                {"Safari", "iOS", "14.3", "iPhone XS Simulator"},
//                {"Safari", "iOS", "14.3", "iPhone SE (2nd generation) Simulator"},
        });
    }

    @Before
    public void setUp() throws MalformedURLException {
        //Configure these using Platform Configurator:
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("appium:platformVersion", platformVersion);
        capabilities.setCapability("appium:deviceName", deviceName);
        capabilities.setCapability("appium:automationName", "XCUITest");

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", EmuSimWebAppTests.SAUCE_USERNAME);
        sauceOptions.setCapability("accesskey", EmuSimWebAppTests.SAUCE_ACCESS_KEY);
        // sauceOptions.setCapability("appiumVersion", "1.22.3");
        sauceOptions.setCapability("build", BUILD_NAME);
        sauceOptions.setCapability("name", testName.getMethodName());
        capabilities.setCapability("sauce:options", sauceOptions);

        driver = new RemoteWebDriver(Endpoints.getSauceHub(), capabilities);
    }

    @Test
    public void loginWorks() {
        // for(int i = 0; i < 40; i++){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.visit();
            loginPage.login("standard_user");
            assertTrue(new ProductsPage(driver).isDisplayed());
        // }
    }

    // @Test(expected = TimeoutException.class)
    public void lockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.login("locked_out_user");
        assertFalse(new ProductsPage(driver).isDisplayed());
    }

    // @Test(expected = TimeoutException.class)
    public void invalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.login("foo_bar_user");
        assertFalse(new ProductsPage(driver).isDisplayed());
    }
}
