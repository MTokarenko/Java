package tokarenko;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class AbstractPage {

    private WebDriver driver;
    protected WebDriverWait waiting;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.waiting = new WebDriverWait(this.driver, 15);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public AbstractPage btnClick(WebElement el) {
        wait("button", el);
        el.click();
        return this;
    }

    protected AbstractPage btnClick(String xpath) {
        wait("button", xpath);
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    protected AbstractPage fieldInsert(WebElement elem, String value) {
        wait("div", elem);
        elem.click();
        elem.clear();
        elem.sendKeys(value);
        return this;
    }

    public AbstractPage fieldInsert(String xpath, String value) {
        WebElement elem =  waiting.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        elem.click();
        elem.clear();
        elem.sendKeys(value);
        return this;
    }

    protected void wait(String type, WebElement el) {
        if (type == "button")
            waiting.until(ExpectedConditions.elementToBeClickable(el));
        else if (type == "div")
            waiting.until(ExpectedConditions.visibilityOf(el));
    }

    protected void wait(String type, String xpath) {
        if (type == "button")
            waiting.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        else if (type == "div")
            waiting.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        else if (type == "divs")
            waiting.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    protected AbstractPage scrollTo(WebElement el) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", el);
        return this;
    }

    protected WebElement findElement(String xPath) {
        return getDriver().findElement(By.xpath(xPath));
    }

    protected List <WebElement> findElements(String xPath) {
        return getDriver().findElements(By.xpath(xPath));
    }

    protected void ctrlF5() {
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
    }
}
