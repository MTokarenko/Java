package haulmont.appmanager;

import haulmont.appmanager.pages.Main;
import haulmont.appmanager.pages.Page;
import haulmont.appmanager.pages.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;

import static tokarenko.haulmont.tezis.data.Data.URL;

/**
 * Created by Mikhail on 01.07.2017.
 */
public class ApplicationManager {

    public WebDriver driver;

    private Login login;
    private Main mainPage;

    public void init() {
        String browser = BrowserType.CHROME;
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        login = new Login(driver);
        mainPage = new Main(driver);
        login.login("admin", "admin");
    }

    public void stop() {
        driver.quit();
    }

    public Login getLogin() {
        return login;
    }

    public Main getMainPage() {
        return mainPage;
    }

}
