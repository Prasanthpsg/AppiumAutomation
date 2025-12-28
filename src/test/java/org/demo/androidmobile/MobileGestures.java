package org.demo.androidmobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MobileGestures {

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
        uiAutomator2Options.setApp(System.getProperty("user.dir")+"/src/test/resources/ApiDemos-debug.apk");
        uiAutomator2Options.setAppPackage("io.appium.android.apis");
        uiAutomator2Options.setAppActivity("io.appium.android.apis.ApiDemos");

        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), uiAutomator2Options);
        Thread.sleep(6000);

        //click
        HashMap<String, Object> hmap = new HashMap<String, Object>();
        hmap.put("elementId", ((RemoteWebElement)driver.findElement(AppiumBy.accessibilityId("Views"))).getId());
        driver.executeScript("mobile: clickGesture", hmap);

        //scroll
     /*   HashMap<String, Object> scrollMap = new HashMap<String, Object>();
        scrollMap.put("elementId", ((RemoteWebElement)driver.findElement(AppiumBy.id("android:id/list"))).getId());
        scrollMap.put("direction", "down");
        scrollMap.put("percent", "0.5");
        driver.executeScript("mobile: scrollGesture", scrollMap);
        driver.findElement(AppiumBy.accessibilityId("ImageView")).click();
        Thread.sleep(3000);*/

        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        HashMap<String, Object> dragMap = new HashMap<String, Object>();
        dragMap.put("elementId", ((RemoteWebElement)driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"))).getId());
        dragMap.put("endX", 600);
        dragMap.put("endY", 641);
        driver.executeScript("mobile: dragGesture", dragMap);
        Thread.sleep(3000);

       String output = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getAttribute("text");
        if(output!=null && !output.equalsIgnoreCase("")){
            System.out.println("The output "+output+" is displayed");
        }else{
            System.out.println("No output");
        }

        driver.navigate().back();
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK));
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.HOME));
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));

        ((AndroidDriver)driver).terminateApp("io.appium.android.apis");

        service.stop();

    }
}
