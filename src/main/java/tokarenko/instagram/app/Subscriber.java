package tokarenko.instagram.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import tokarenko.instagram.pages.FaceBookLoginPage;
import tokarenko.instagram.pages.HomePage;
import tokarenko.instagram.pages.InstagramLoginPage;


public class Subscriber {
    public static void main(String... args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://instagram.com");
            InstagramLoginPage instagramLoginPage = PageFactory.initElements(driver, InstagramLoginPage.class);
            instagramLoginPage.click_loginFromFacebookButton();

            PageFactory.initElements(driver, FaceBookLoginPage.class)
                    .login("Touringeo@mail.ru", "13085757solnishko13");
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);
            homePage.addFollowers();
            driver.quit();

        } catch (Throwable t) {
            t.printStackTrace();
            driver.quit();
        }
    }
}
