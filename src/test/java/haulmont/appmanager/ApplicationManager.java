package haulmont.appmanager;

import haulmont.appmanager.pages.*;
import haulmont.appmanager.pages.references.Employees;
import haulmont.appmanager.pages.references.Substitutions;
import haulmont.appmanager.pages.references.TypicalResolution;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import static tokarenko.haulmont.tezis.data.Data.URL;

/**
 * Created by Mikhail on 01.07.2017.
 */
public class ApplicationManager {

    public WebDriver driver;
    private String browser;

    private Login login;
    private Main mainPage;
    private NewUser newUser;
    private Substitutions substitutions;
    private TypicalResolution typicalResolution;
    private Employees employees;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if (browser.equals(BrowserType.CHROME)) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browser.equals(BrowserType.IE)) {
            System.setProperty("webdriver.ie.driver", "src\\main\\resources\\drivers\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        driver.get(URL);
        driver.manage().window().maximize();

        login = new Login(driver);
        mainPage = new Main(driver);
        newUser = new NewUser(driver);
        substitutions = new Substitutions(driver);
        typicalResolution = new TypicalResolution(driver);
        employees = new Employees(driver);

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

    public NewUser getNewUser() {
        return newUser;
    }

    public Substitutions getSubstitutions() {
        return substitutions;
    }

    public TypicalResolution getTypicalResolution() {
        return typicalResolution;
    }

    public Employees getEmployees() {
        return employees;
    }
}
