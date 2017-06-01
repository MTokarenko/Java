package tokarenko.instagram.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import tokarenko.AbstractPage;
import static utils.Utils.*;


public class InstagramHomePage extends AbstractPage {

    @FindBy(xpath = ".//a[contains(text(), 'Instagram')]")
    protected WebElement titleInstagram;

    @FindBy(xpath = ".//a[contains(@href, '/followers/')]")
    protected WebElement followers;

    private final String URL_BASE;

    public InstagramHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        URL_BASE = driver.getCurrentUrl();
        waiting.until(ExpectedConditions.visibilityOf(titleInstagram));
    }

    public void addFollowers() {
        for (String s : getListGroups()) {
            getDriver().get(URL_BASE.concat(s));
            waiting.until(ExpectedConditions.visibilityOf(followers));
            followers.click();
            followersAdd();
        }
    }

    private void followersAdd() {
        WebElement followers = getDriver().findElement(By.xpath(".//div[contains(text(), 'Подписчики')]"));
        waiting.until(ExpectedConditions.visibilityOf(followers));
        WebElement el;
        while (true) {
            try {
                el = getDriver().findElement(By.xpath("(.//span[@class = '_7k49n']/*[contains(text(), 'Подписаться')])[1]"));
            } catch (NoSuchElementException ex) {
                break;
            }
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", el);
            el.click();
            sleep(10);
        }
    }


    private List<String> getListGroups() {
        List<String> list = new ArrayList<String>();
        list.add("travelingru");
        return list;
    }

}
