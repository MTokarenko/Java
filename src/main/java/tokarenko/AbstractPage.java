package tokarenko;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AbstractPage {

    private WebDriver driver;
    public WebDriverWait wait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 15);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public AbstractPage fieldInsert(WebElement el, String value) {
        el.clear();
        el.sendKeys(value);
        return this;
    }

    public void fieldInsert(String xpath, String value) {
        WebElement elem =  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        elem.clear();
        elem.sendKeys(value);
    }
}
