package org.saucelabs.androidtests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    private AppiumDriverLocalService service =null;
    protected AppiumDriver driver = null;
    protected  UiAutomator2Options uiAutomator2Options = null;

    @BeforeTest
    public void initDriver() throws MalformedURLException, InterruptedException {
         service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\HP\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();

        service.start();

        uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setAvd("Medium_Phone_API_36.1");
        uiAutomator2Options.setChromedriverExecutable(System.getProperty("user.dir")+"/src/test/resources/Executables/chromedriver-win64/chromedriver.exe");
        uiAutomator2Options.setApp(System.getProperty("user.dir")+"/src/test/resources/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        uiAutomator2Options.setAppPackage("com.swaglabsmobileapp");
        uiAutomator2Options.setAppActivity("com.swaglabsmobileapp.MainActivity");
    }


    @AfterTest
    public void tearDownService(){
        service.stop();

    }
}
