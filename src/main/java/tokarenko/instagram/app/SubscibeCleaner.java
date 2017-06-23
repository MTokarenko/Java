package tokarenko.instagram.app;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import tokarenko.instagram.pages.FaceBookLoginPage;
import tokarenko.instagram.pages.HomePage;
import tokarenko.instagram.pages.InstagramLoginPage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static utils.Utils.print;
import static utils.Utils.sleep;

public class SubscibeCleaner {
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
            homePage.openProfile();
            List<String> list = homePage.getSubscribers();
            print(list.size());
            print(list);
            sleep(3);
            driver.quit();

        } catch (Throwable t) {
            t.printStackTrace();
            driver.quit();
        }
    }
}