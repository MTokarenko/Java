import haulmont.appmanager.pages.Login;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import tokarenko.haulmont.tezis.pages.LoginPage;
import tokarenko.haulmont.tezis.pages.MainPage;

import java.util.List;

import static tokarenko.haulmont.tezis.data.Data.URL;

public class Sandbox {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(URL);
            driver.manage().window().maximize();
            Login loginPage = new Login(driver);
            loginPage.login("admin", "admin");
        } catch (Throwable t) {
            t.printStackTrace();

        } finally {
            driver.quit();
        }
    }
}