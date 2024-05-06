package com.saucedemo;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL to the desired Data Center.
 */
public class Endpoints {

    /**
     * Sauce Labs Hub.
     *
     * @return URL for Sauce Labs Hub.
     */
    public static URL getSauceHub() throws MalformedURLException {
        String endpoint = "https://ondemand." + System.getenv().getOrDefault("SAUCE_REGION", "us-west-1") + ".saucelabs.com:443/wd/hub";
        return new URL(endpoint);
    }
}

