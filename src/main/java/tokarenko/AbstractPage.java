package tokarenko;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AbstractPage {

    private WebDriver driver;
    public WebDriverWait waiting;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.waiting = new WebDriverWait(this.driver, 10);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public AbstractPage btnClick(WebElement el) {
        wait("button", el);
        el.click();
        return this;
    }

    public AbstractPage btnClick(String xpath) {
        wait("button", xpath);
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public AbstractPage fieldInsert(WebElement el, String value) {
        wait("div", el);
        el.clear();
        el.sendKeys(value);
        return this;
    }

    public AbstractPage fieldInsert(String xpath, String value) {
        WebElement elem =  waiting.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        elem.clear();
        elem.sendKeys(value);
        return this;
    }

    public void wait(String type, WebElement el) {
        if (type == "button")
            waiting.until(ExpectedConditions.elementToBeClickable(el));
        else if (type == "div")
            waiting.until(ExpectedConditions.visibilityOf(el));
    }

    public void wait(String type, String xpath) {
        if (type == "button")
            waiting.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        else if (type == "div")
            waiting.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }
}