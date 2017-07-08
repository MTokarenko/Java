import haulmont.appmanager.pages.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import static tokarenko.haulmont.tezis.data.Data.TEZIS_BTN;
import static tokarenko.haulmont.tezis.data.Data.URL;

public class Sandbox {
    public static void main(String[] args) {


        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(URL);
            driver.manage().window().maximize();
            Login loginPage = new Login(driver);
            loginPage.login("doc_initiator", "123");
            Assert.assertTrue(driver.findElement(By.xpath(TEZIS_BTN)).isDisplayed(), "Nop");
            System.out.println("OK");
            try {
                String currentUser = driver.findElement(By.xpath(".//div[@cuba-id=\"substitutedUserSelect\"]/input")).getAttribute("value");
            } catch (NoSuchElementException ex) {
                String currentUser = driver.findElement(By.xpath(".//div[@cuba-id=\"currentUserLabel\"]")).getText();
            }

        } catch (Throwable t) {
            t.printStackTrace();

        } finally {
            driver.quit();
        }
    }
}