import haulmont.appmanager.pages.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static tokarenko.haulmont.tezis.data.Data.TEZIS_BTN;
import static tokarenko.haulmont.tezis.data.Data.URL;
import static utils.Utils.sleep;

public class Sandbox {
    public static void main(String[] args) {
        String str = new String("initiator g. g. [init]");
        str = str.split(" ")[0];
        System.out.println("final - " + str);



        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        try {
            String currentUser;
            driver.get(URL);
            driver.manage().window().maximize();
            Login loginPage = new Login(driver);
            loginPage.login("admin", "admin");
            Assert.assertTrue(driver.findElement(By.xpath(TEZIS_BTN)).isDisplayed(), "Nop");
            System.out.println("OK");
            try {
                currentUser = driver.findElement(By.xpath(".//div[@cuba-id=\"substitutedUserSelect\"]/input"))
                        .getAttribute("value");
            } catch (NoSuchElementException ex) {
                currentUser = driver.findElement(By.xpath(".//div[@cuba-id=\"currentUserLabel\"]")).getText();
            }
            currentUser = currentUser.split(" ")[0];
            System.out.println(currentUser);
            Actions action = new Actions(driver);
            action.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
            sleep(3);
            System.out.println("done");

        } catch (Throwable t) {
            t.printStackTrace();

        } finally {
            driver.quit();
        }
    }
}