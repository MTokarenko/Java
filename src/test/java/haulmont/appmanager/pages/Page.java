package haulmont.appmanager.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Mikhail on 01.07.2017.
 */
public class Page {

    protected WebDriverWait waiting;
    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.waiting = new WebDriverWait(driver, 15);
    }


    public Page btnClick(WebElement el) {
        wait("button", el);
        el.click();
        return this;
    }

    public Page btnClick(String xpath) {
        wait("button", xpath);
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public Page fieldInsert(WebElement elem, String value) {
        wait("div", elem);
        if (value != null) {
            String existingText = elem.getAttribute("value");
            if (! value.equals(existingText)) {
                elem.click();
                elem.clear();
                elem.sendKeys(value);
            }
        }
        return this;
    }

    public Page fieldInsert(String xpath, String value) {
        WebElement elem =  waiting.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        if (value != null) {
            String existingText = elem.getAttribute("value");
            if (! value.equals(existingText)) {
                elem.click();
                elem.clear();
                elem.sendKeys(value);
            }
        }
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
        else if (type == "divs")
            waiting.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public Page scrollTo(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        return this;
    }

    public WebElement findElement(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

    public List<WebElement> findElements(String xPath) {
        return driver.findElements(By.xpath(xPath));
    }

    public void ctrlF5() {
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
    }

    public boolean isCurrentScreen(String screenCubaId) {
        try{
            findElement(String.format(".//td[@cuba-id=\"%s\"][@aria-selected=\"true\"]", screenCubaId));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException t) {
            return false;
        }
    }
}
