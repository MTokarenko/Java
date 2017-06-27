package tokarenko.instagram.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import tokarenko.instagram.pages.FaceBookLoginPage;
import tokarenko.instagram.pages.HomePage;
import tokarenko.instagram.pages.InstagramLoginPage;

import java.util.Arrays;
import java.util.List;

import static tokarenko.instagram.data.Data.groupsNames;


public class Subscriber {
    public static void main(String... args) {
//        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get("http://instagram.com");
            InstagramLoginPage instagramLoginPage = PageFactory.initElements(driver, InstagramLoginPage.class);
            instagramLoginPage.click_loginFromFacebookButton();

            PageFactory.initElements(driver, FaceBookLoginPage.class)
                    .login("Touringeo@mail.ru", "13085757solnishko13");
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);
            homePage.openProfile();
            List<String> myFollowers = homePage.getSubscribers();
            for (String s : groupsNames) {
                driver.get(homePage.URL_BASE.concat(s));
                homePage.addFollowers(myFollowers);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            driver.quit();
        }
    }
}
