package haulmont;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTest {


    @Before
    public void setUp() {
        System.out.println("begin!");
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        System.out.println("End!");
    }
}
