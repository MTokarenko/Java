import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import tokarenko.haulmont.tezis.pages.LoginPage;
import tokarenko.haulmont.tezis.pages.MainPage;
import tokarenko.haulmont.tezis.pages.NewUserScreen;

import java.util.ArrayList;
import java.util.List;

import static tokarenko.haulmont.tezis.data.Data.EMAIL;
import static tokarenko.haulmont.tezis.data.Data.URL;

public class Sandbox {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(URL);
            driver.manage().window().maximize();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("admin", "admin");
//            MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
            MainPage mainPage = new MainPage(driver);
            mainPage.openUsersScreen();
            List users = mainPage.getRowFromTable("3");
            System.out.println(users.size());

        } catch (Throwable t) {
            t.printStackTrace();

        } finally {
            driver.quit();
        }
    }
}