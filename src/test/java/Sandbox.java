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
//        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(URL);
            driver.manage().window().maximize();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("admin", "admin");
            System.out.println("Click CTRL + F5");
            Actions action = new Actions(driver);
            action.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
            MainPage mainPage = new MainPage(driver);
            mainPage.openUsersScreen();
            List users = mainPage.getRowsFromLongTable("3");
            System.out.println(users.size());

        } catch (Throwable t) {
            t.printStackTrace();

        } finally {
            driver.quit();
        }
    }
}