package haulmont.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Properties;

public class Init {

    private static WebDriver driver;
    private String typeDriver = Prop.get("base.type_driver");


    public Init() {
    }

    public static WebDriver getWebDriver() {
        if (null == driver) {
            driver = createDriver();
        }
        return driver;
    }

    private WebDriver createDriver() {
        DesiredCapabilities capabilities = (new DesiredCapabilities()).parse();
        File chrome;
        File ie;
        switch (typeDriver.hashCode()) {
            case 0: //ie
                ie = new File("src/test/resources/webdrivers/IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", ie.getAbsolutePath());
                capabilities.setBrowserName("IE");
                setDriver(new InternetExplorerDriver(capabilities));
            case 1: //firefox
                capabilities.setBrowserName("FireFox");
                setDriver(new FirefoxDriver(capabilities));
            case 2: //chrome
                chrome = new File("src/test/resources/webdrivers/chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
                capabilities.setBrowserName("Chrome");
                setDriver(new ChromeDriver(capabilities));
        }
    }

    public static void setDriver(WebDriver d) {
        driver = d;
    }

}
