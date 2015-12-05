package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.isHtmlUnit;

public class TestBase {
    private final static String DEFAULT_URL="http://the-internet.herokuapp.com/";
    private Properties environment;


    @BeforeTest
    public void configure() {
        Configuration.timeout = 10000;
        Configuration.baseUrl = System.getProperty("baseURL", DEFAULT_URL);
        getEnvironmentProperties();
//        Configuration.browser = WebDriverRunner.CHROME;
    }

    @AfterTest
    public void cleanup() {
        saveEnvironment();
    }

    private void getEnvironmentProperties() {
        environment = new Properties();
        if(!isHtmlUnit()) {
            Capabilities caps = ((RemoteWebDriver)getWebDriver()).getCapabilities();
            environment.put("browser", caps.getBrowserName());
            environment.put("browser.version", caps.getVersion());
            environment.put("platform", caps.getPlatform().name());
            environment.put("platform.version", caps.getPlatform().getMajorVersion() + "." +
                    caps.getPlatform().getMinorVersion());
            environment.put("url", Configuration.baseUrl);
        } else {
            environment.put("browser", "htmlUnit");
        }
    }

    private void saveEnvironment() {
        File resultsFolder = new File("./target/allure-results/");
        if (!resultsFolder.exists()) {
            resultsFolder.mkdirs();
        }
        try (OutputStream out = new FileOutputStream("./target/allure-results/environment.properties");) {
            environment.store(out, "Allure environment properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
