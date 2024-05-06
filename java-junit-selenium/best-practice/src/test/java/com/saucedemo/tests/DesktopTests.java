package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucelabs.saucebindings.Browser;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBaseTest;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.TimeoutException;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.time.Instant;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Desktop Tests. */
@RunWith(Parameterized.class)
public class DesktopTests extends SauceBaseTest {
    /*
     * Configure our data driven parameters
     * */
    @Parameterized.Parameter
    public Browser browserName;
    @Parameterized.Parameter(1)
    public String browserVersion;
    @Parameterized.Parameter(2)
    public SaucePlatform platform;
    protected static final String BUILD_NAME = "Java Best Practice Demo" + " " + System.getenv().getOrDefault("SAUCE_BUILD_TYPE", "Local") + " Build #" + System.getenv().getOrDefault("SAUCE_RUN_NUMBER", String.valueOf(System.currentTimeMillis()));


    @Parameterized.Parameters()
    public static Collection<Object[]> crossBrowserData() {
        return Arrays.asList(new Object[][] {
                { Browser.CHROME, "latest",   SaucePlatform.WINDOWS_10 },
                { Browser.CHROME, "latest-1", SaucePlatform.WINDOWS_10 },
                { Browser.SAFARI, "latest",   SaucePlatform.MAC_MOJAVE },
                { Browser.CHROME, "latest",   SaucePlatform.MAC_MOJAVE },
                
                         // Duplication below for demo purposes of massive parallelization
                         {Browser.CHROME, "latest",   SaucePlatform.WINDOWS_10},
                         {Browser.CHROME, "latest-1", SaucePlatform.WINDOWS_10},
                         {Browser.SAFARI, "latest",   SaucePlatform.MAC_MOJAVE},
                         {Browser.CHROME, "latest",   SaucePlatform.MAC_MOJAVE},
                         {Browser.CHROME, "latest",   SaucePlatform.WINDOWS_10},
                         {Browser.CHROME, "latest-1", SaucePlatform.WINDOWS_10},
                         {Browser.SAFARI, "latest",   SaucePlatform.MAC_MOJAVE},
                         {Browser.CHROME, "latest",   SaucePlatform.MAC_MOJAVE}
                         /*
                         {Browser.CHROME, "latest",   SaucePlatform.WINDOWS_10},
                         {Browser.CHROME, "latest-1", SaucePlatform.WINDOWS_10},
                         {Browser.SAFARI, "latest",   SaucePlatform.MAC_MOJAVE},
                         {Browser.CHROME, "latest",   SaucePlatform.MAC_MOJAVE},
                         {Browser.CHROME, "latest",   SaucePlatform.WINDOWS_10},
                         {Browser.CHROME, "latest-1", SaucePlatform.WINDOWS_10},
                         {Browser.SAFARI, "latest",   SaucePlatform.MAC_MOJAVE},
                         {Browser.CHROME, "latest",   SaucePlatform.MAC_MOJAVE},
                         {Browser.CHROME, "latest",   SaucePlatform.WINDOWS_10},
                         {Browser.CHROME, "latest-1", SaucePlatform.WINDOWS_10},
                         {Browser.SAFARI, "latest",   SaucePlatform.MAC_MOJAVE},
                         {Browser.CHROME, "latest",   SaucePlatform.MAC_MOJAVE},
                */
        });
    }

    @Override
    public SauceOptions createSauceOptions() {
        SauceOptions sauceOptions = new SauceOptions();
        sauceOptions.setBrowserName(browserName);
        sauceOptions.setBrowserVersion(browserVersion);
        sauceOptions.setPlatformName(platform);
        sauceOptions.sauce().setBuild(BUILD_NAME);
        return sauceOptions;
    }

    @Override
    public void setup() {
        SauceOptions sauceOptions = createSauceOptions();
        if (sauceOptions.sauce().getName() == null) {
            sauceOptions.sauce().setName(testName.getMethodName());
        }

        session = new SauceSession(sauceOptions);
        String endpoint = "https://ondemand." + System.getenv().getOrDefault("SAUCE_REGION", "us-west-1") + ".saucelabs.com:443/wd/hub";
        if(endpoint != null) {
            try {
                this.session.setSauceUrl(new URL(endpoint));
            } catch (MalformedURLException e) {
                throw new InvalidArgumentException("Invalid URL");
            }
        }
        
        driver = session.start();
    }

    @Test()
    public void loginWorksMultiPass() {
        for(int i = 0; i < 40; i++){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.visit();
            loginPage.login("standard_user");
            assertTrue(new ProductsPage(driver).isDisplayed());
        }
    }

    @Test()
    public void loginWorks() {
        for(int i = 0; i < 2; i++){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.visit();
            loginPage.login("standard_user");
            assertTrue(new ProductsPage(driver).isDisplayed());
            System.out.println("Checked Login Page " + i + "  times");
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
