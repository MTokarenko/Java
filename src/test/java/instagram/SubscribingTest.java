package instagram;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import tokarenko.instagram.pages.FaceBookLoginPage;
import tokarenko.instagram.pages.InstagramHomePage;
import tokarenko.instagram.pages.InstagramLoginPage;


public class SubscribingTest {

    public static void main(String... args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://instagram.com");

            InstagramLoginPage instagramLoginPage = PageFactory.initElements(driver, InstagramLoginPage.class);
            instagramLoginPage.click_loginFromFacebookButton();

            PageFactory.initElements(driver, FaceBookLoginPage.class)
                    .login("Touringeo@mail.ru", "13085757solnishko13");
            PageFactory.initElements(driver, InstagramHomePage.class).addFollowers();

        } catch (Throwable t) {
            t.printStackTrace();
            driver.quit();
        }
    }
}
