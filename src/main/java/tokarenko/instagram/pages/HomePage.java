package tokarenko.instagram.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tokarenko.AbstractPage;

import java.util.*;

import static tokarenko.instagram.data.Data.groupsNames;
import static utils.Utils.sleep;


public class HomePage extends AbstractPage {

    @FindBy(xpath = ".//a[contains(text(), 'Instagram')]")
    protected WebElement titleInstagram;

    @FindBy(xpath = ".//a[contains(@href, '/followers/')]")
    protected WebElement followers;

    @FindBy(xpath = ".//a[.=\"Профиль\"]")
    protected WebElement profileBtn;

    private final String URL_BASE;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        URL_BASE = driver.getCurrentUrl();
        wait("div", titleInstagram);
    }

    public void addFollowers() {
        for (String s : groupsNames) {
            getDriver().get(URL_BASE.concat(s));
            btnClick(followers);
            wait("divs", ".//li[@class=\"_cx1ua\"]");
            WebElement el;
            while (true) {
                try {
                    el = getDriver().findElement(By
                            .xpath("(.//span[@class = '_7k49n']/*[contains(text(), 'Подписаться')])[1]"));
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", el);
                    el.click();
                    sleep(3);
                    if (el.getText().equals("Подписаться")) {
                        getDriver().navigate().refresh();
                        addFollowers();
                    }
                    sleep(7);
                } catch (org.openqa.selenium.NoSuchElementException ex) {
                    gotoLastElemet(".//span[@class = '_7k49n']/button");
                    sleep(1);
                }

            }
        }
    }

    private void gotoLastElemet(String xpathExpression) {
        List<WebElement> rowsFollowers = getDriver()
                .findElements(By.xpath(xpathExpression));
        WebElement lastElement = rowsFollowers.get(rowsFollowers.size()-1);
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", lastElement);
    }

    public void openProfile() {
        btnClick(profileBtn);
        wait("button", ".//button[.=\"Редактировать профиль\"]");
    }

    public List<String> getSubscribers() {
        String checker = "stringForChecking";
        btnClick(followers);
        wait("divs", ".//li[@class=\"_cx1ua\"]");
        Set<String> followers = new HashSet<String>();
        List<WebElement> followersTmp = getDriver()
                .findElements(By.xpath(".//a[@class=\"_4zhc5 notranslate _j7lfh\"]"));
        WebElement lastElement = followersTmp.get(1);
        while (! checker.equals(lastElement.getText())) {
            checker = lastElement.getText();
            followersTmp = getDriver()
                    .findElements(By.xpath(".//a[@class=\"_4zhc5 notranslate _j7lfh\"]"));
            lastElement = followersTmp.get(followersTmp.size()-1);
            for (WebElement follower: followersTmp) {
                followers.add(follower.getText());
            }
            ((JavascriptExecutor) getDriver())
                    .executeScript("arguments[0].scrollIntoView(true);", lastElement);
            sleep(1);
        }
        return new ArrayList<>(followers);
    }
}
