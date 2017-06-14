package tokarenko.haulmont.tezis.app;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import tokarenko.haulmont.tezis.pages.LoginPage;
import tokarenko.haulmont.tezis.pages.MainPage;
import tokarenko.haulmont.tezis.pages.SubstitutionScreen;

import java.util.List;

import static tokarenko.haulmont.tezis.data.Data.URL;

public class UsersSubstitutionCreation {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(URL);
            driver.manage().window().maximize();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("admin", "admin");
            MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
            mainPage.openSubstitutionScreen();
            SubstitutionScreen substitutionScreen = new SubstitutionScreen(driver);
            substitutionScreen.createSubstitutions();
        } catch (Throwable t) {
            t.printStackTrace();

        }finally {
            driver.quit();
        }
    }
}
