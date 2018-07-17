package nnglebanov.aplana.driverfactory;

import nnglebanov.aplana.G_VARIABLES;
import nnglebanov.aplana.driverfactory.enums.Browsers;
import nnglebanov.aplana.driverfactory.enums.Environment;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.FileInputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class DriverFactory {

    private static DriverFactory instance = null;
    private static final int IMPLICIT_TIMEOUT = 0;

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();
    private ThreadLocal<String> sessionBrowser = new ThreadLocal<String>();
    private ThreadLocal<String> sessionPlatform = new ThreadLocal<String>();
    private ThreadLocal<String> sessionVersion = new ThreadLocal<String>();

    private Environment getEnv = null;
    private Properties props = new Properties();

    private DriverFactory() {
    }

    public static DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }

        return instance;
    }


    public final void setDriver(Browsers browser,
                                String platform,
                                Environment environment,
                                Map<String, Object>... optPreferences)
            throws Exception {

        MutableCapabilities caps = null;
        props.load(new FileInputStream(G_VARIABLES.SE_PROPS));

        if (environment.equals(Environment.LOCAL)) {
            switch (browser) {
                case FIREFOX:
                    caps = new FirefoxOptions();
                    System.setProperty("webdriver.gecko.driver", props.getProperty("gecko.driver.windows.path"));
                    webDriver.set(new FirefoxDriver((FirefoxOptions) caps));

                    break;
                case CHROME:
                    caps = new ChromeOptions();
                    System.setProperty("webdriver.chrome.driver", props.getProperty("chrome.driver.windows.path"));
                    webDriver.set(new ChromeDriver((ChromeOptions) caps));

                    break;
                case IE:
                    caps = new InternetExplorerOptions();
                    System.setProperty("webdriver.ie.driver", props.getProperty("ie.driver.windows.path"));
                    webDriver.set(new InternetExplorerDriver((InternetExplorerOptions) caps));

                    break;
            }
        }

        getEnv = environment;
        String getPlatform = platform;
        sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());
        sessionBrowser.set(caps.getBrowserName());
        sessionVersion.set(caps.getVersion());
        sessionPlatform.set(getPlatform);

        System.out.println("\n*** TEST ENVIRONMENT = "
                + getSessionBrowser().toUpperCase()
                + "/" + getSessionPlatform().toUpperCase()
                + "/" + getEnv.toString()
                + "/Selenium Version=" + props.getProperty("selenium.revision")
                + "/Session ID=" + getSessionId() + "\n");

        getDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }


    public WebDriver getDriver() {
        return webDriver.get();
    }


    public void closeDriver() {
        try {
            getDriver().quit();
        } catch (Exception e) {
        }
    }


    public String getSessionId() throws Exception {
        return sessionId.get();
    }


    public String getSessionBrowser() throws Exception {
        return sessionBrowser.get();
    }

    public String getSessionVersion() throws Exception {
        return sessionVersion.get();
    }

    public String getSessionPlatform() throws Exception {
        return sessionPlatform.get();
    }

}