package tokarenko.haulmont.tezis.app;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import tokarenko.haulmont.tezis.pages.LoginPage;
import tokarenko.haulmont.tezis.pages.MainPage;

import java.util.List;

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
            mainPage.openUsersScreen()
                    .btnClick(".//div[@cuba-id=\"createPopupButton\"]")
                    .btnClick(".//div[@cuba-id=\"create\"]")
                    .btnClick(".//div[@cuba-id=\"rolesTableAddBtn\"]");
            sleep(3);
            List<WebElement> el = driver.findElements(By.xpath(
                    ".//table[@class=\"v-table-table\"]//tr[contains(@class, 'v-table')]/td[1]"));
            for (WebElement element: el)
                print(element.getText());
            sleep(3);

        } catch (Throwable t) {
            t.printStackTrace();

        }finally {
            driver.quit();
        }
    }
}