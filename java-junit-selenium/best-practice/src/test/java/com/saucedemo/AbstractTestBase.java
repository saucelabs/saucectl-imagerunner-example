package com.saucedemo;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * All Tests need to extend this class to get the correct behavior.
 */
public abstract class AbstractTestBase {
    @Rule
    public TestName testName = new TestName() {
        public String getMethodName() {
            return String.format("%s", super.getMethodName());
        }
    };
    @Rule
    public SauceTestWatcher resultReportingTestWatcher = new SauceTestWatcher();

    protected static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
    protected static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    protected static final String BUILD_NAME = "Java Best Practice Demo" + " " + System.getenv().getOrDefault("SAUCE_BUILD_TYPE", "Local Build") + " " + System.currentTimeMillis();

    protected RemoteWebDriver driver;

    /**
     * Custom TestWatcher for Sauce Labs projects.
     */
    public class SauceTestWatcher extends TestWatcher {
        @Override
        protected void succeeded(Description description) {
            if (driver != null) {
                driver.executeScript("sauce:job-result=passed");
                driver.quit();
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            if (driver != null) {
                driver.executeScript("sauce:job-result=failed");
                driver.quit();
            }
        }
    }
}
