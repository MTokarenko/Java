package tokarenko.haulmont.tezis.app;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import tokarenko.haulmont.tezis.pages.LoginPage;
import tokarenko.haulmont.tezis.pages.MainPage;

import static tokarenko.haulmont.tezis.data.Data.*;
import static utils.Utils.*;

public class UsersAndRolesCreation {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(URL);
            driver.manage().window().maximize();
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            loginPage.login("admin", "admin");
            MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
            mainPage.openUsersScreen();
            sleep(3);

        } catch (Throwable t) {
            t.printStackTrace();

        }finally {
            driver.quit();
        }
    }
}
