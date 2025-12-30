package org.demo.androidmobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BrowserTesting {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //start appium server through code
        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\HP\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();

        service.start();

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setAvd("Medium_Phone_API_36.1");
        uiAutomator2Options.setChromedriverExecutable(System.getProperty("user.dir")+"/src/test/resources/Executables/chromedriver-win64/chromedriver.exe");
        uiAutomator2Options.setCapability("browserName", "Chrome");

        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), uiAutomator2Options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.quit();
        service.stop();
    }
}
