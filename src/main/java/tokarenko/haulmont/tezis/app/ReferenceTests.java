package tokarenko.haulmont.tezis.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tokarenko.haulmont.tezis.pages.LoginPage;
import tokarenko.haulmont.tezis.pages.MainPage;
import tokarenko.haulmont.tezis.pages.NewUserScreen;

import java.util.Arrays;
import java.util.List;

import static tokarenko.haulmont.tezis.data.Data.URL;
import static utils.Utils.sleep;

/**
 * Created by tokarenko on 30.06.2017.
 */
public class ReferenceTests {

    private static List<String> ignoredReferences = Arrays.asList("Замещение пользователей", "Приоритеты");

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(URL);
            driver.manage().window().maximize();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("admin", "admin");
            MainPage mainPage = new MainPage(driver);
            List<String> referenceAdmin = mainPage.getReferences();
            referenceAdmin.removeAll(ignoredReferences);
            NewUserScreen newUser = new NewUserScreen(driver);
            mainPage.openUsersScreen();
            newUser.createUser("SimpleUser");
            loginPage.relogin("SimpleUser", "123");
            List<String> referenceSimpleUser = mainPage.getReferences();
            if(referenceAdmin.size() == referenceSimpleUser.size() && referenceAdmin.containsAll(referenceSimpleUser)){
                System.out.println("OK");
            }else System.out.println("FALSE");
        }catch (Throwable t) {
            t.printStackTrace();
        }finally {
            driver.quit();
        }
    }
}
