package org.demo.androidmobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidSpecificLocator {

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setAvd("Medium_Phone_API_36.1");
        uiAutomator2Options.setApp(System.getProperty("user.dir")+"/src/test/resources/ApiDemos-debug.apk");
        uiAutomator2Options.setAppPackage("io.appium.android.apis");
        uiAutomator2Options.setAppActivity("io.appium.android.apis.ApiDemos");

        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), uiAutomator2Options);
        Thread.sleep(6000);
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Views\")")).click(); //content-desc
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Date Widgets\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"1. Dialog\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"io.appium.android.apis:id/pickDate\")"))
                .click(); // resource id
        Thread.sleep(5000);
        driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"17 December 2025\"]")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Cancel\")")).click();
        ((AndroidDriver)driver).terminateApp("io.appium.android.apis");
    }
}
