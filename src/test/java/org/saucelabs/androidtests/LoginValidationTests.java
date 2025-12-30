package org.saucelabs.androidtests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginValidationTests extends BaseTest {

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException, InterruptedException {
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), uiAutomator2Options);
        Thread.sleep(6000);
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"test-LOGIN\")")).click();
        Thread.sleep(3000);
    }

    @AfterMethod
    public void afterMethod(){
    ((AndroidDriver)driver).terminateApp("com.swaglabsmobileapp");
    }
}
