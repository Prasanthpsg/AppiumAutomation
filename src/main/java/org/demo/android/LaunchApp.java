package org.demo.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class LaunchApp {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setAvd("Medium_Phone_API_36.1");
        uiAutomator2Options.setApp(System.getProperty("user.dir")+"/src/test/resources/ApiDemos-debug.apk");
        uiAutomator2Options.setAppPackage("io.appium.android.apis");
        uiAutomator2Options.setAppActivity("io.appium.android.apis.ApiDemos");

        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), uiAutomator2Options);
        Thread.sleep(6000);
        ((AndroidDriver)driver).terminateApp("io.appium.android.apis");

    }
}
