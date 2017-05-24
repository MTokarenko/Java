package haulmont;


import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import tokarenko.haulmont.tezis.pages.LoginPage;

import static tokarenko.haulmont.tezis.data.Data.*;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(URL);
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            loginPage.login("admin", "admin");
            Assert.assertTrue("title should be 'ТЕЗИС'", driver.getTitle().equals("ТЕЗИС"));
            driver.quit();

        }catch (Throwable t) {
            t.printStackTrace();
            driver.quit();
        }
    }
}
