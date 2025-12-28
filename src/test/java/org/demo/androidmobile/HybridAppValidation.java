package org.demo.androidmobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class HybridAppValidation {

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
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
        uiAutomator2Options.setApp(System.getProperty("user.dir")+"/src/test/resources/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        uiAutomator2Options.setAppPackage("com.swaglabsmobileapp");
        uiAutomator2Options.setAppActivity("com.swaglabsmobileapp.MainActivity");

        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), uiAutomator2Options);
        Thread.sleep(6000);
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"test-LOGIN\")")).click();
        Thread.sleep(3000);

        driver.findElement(AppiumBy.accessibilityId("test-Menu")).click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.accessibilityId("test-WEBVIEW")).click();
        Thread.sleep(2000);

        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-enter a https url here...\"]")).sendKeys("https://www.google.co.in");
        driver.findElement(AppiumBy.accessibilityId("test-GO TO SITE")).click();
        Thread.sleep(6000);

        System.out.println("Current context: "+ ((AndroidDriver)driver).getContext());

        Set<String> contexts = ((AndroidDriver)driver).getContextHandles();
        for(String context: contexts){
            if(context.contains("WEBVIEW")){
                ((AndroidDriver)driver).context(context);
                break;
            }
        }

        System.out.println("After context switch: "+ ((AndroidDriver)driver).getContext());
        driver.findElement(By.name("q")).sendKeys("UAE", Keys.ENTER);
        Thread.sleep(6000);

        ((AndroidDriver)driver).context("NATIVE_APP");

        ((AndroidDriver)driver).terminateApp("com.swaglabsmobileapp");
        service.stop();
    }
}
