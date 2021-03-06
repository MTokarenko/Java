package tokarenko.haulmont.tezis.app;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tokarenko.haulmont.tezis.docs.Doc;
import tokarenko.haulmont.tezis.pages.LoginPage;

import static tokarenko.haulmont.tezis.data.Data.URL;
import static utils.Utils.sleep;

public class CreateDocumentTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(URL);
            driver.manage().window().maximize();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("admin", "admin");
            Doc doc = new Doc(driver);
            doc.createDocument("Письмо");
            doc.fillInputs();
            sleep(2);
        }catch (Throwable t) {
            t.printStackTrace();
        }finally {
            driver.quit();
        }

    }
}
