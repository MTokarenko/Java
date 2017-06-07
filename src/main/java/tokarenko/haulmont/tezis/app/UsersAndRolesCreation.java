package tokarenko.haulmont.tezis.app;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import tokarenko.haulmont.tezis.pages.LoginPage;
import tokarenko.haulmont.tezis.pages.MainPage;
import tokarenko.haulmont.tezis.pages.NewUserScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static tokarenko.haulmont.tezis.data.Data.*;
import static utils.Utils.*;

public class UsersAndRolesCreation {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Boolean roleChecker = Boolean.FALSE;
        int counter;
        try {
            driver.get(URL);
            driver.manage().window().maximize();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("admin", "admin");
            MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
            mainPage.openUsersScreen();
            NewUserScreen newUser = new NewUserScreen(driver);
            newUser.createUsers(EMAIL);

        } catch (Throwable t) {
            t.printStackTrace();

        }finally {
            driver.quit();
        }
    }
}