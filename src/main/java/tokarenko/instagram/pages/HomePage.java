package tokarenko.instagram.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tokarenko.AbstractPage;

import java.util.*;

import static utils.Utils.sleep;


public class HomePage extends AbstractPage {

    @FindBy(xpath = ".//a[contains(text(), 'Instagram')]")
    protected WebElement titleInstagram;

    @FindBy(xpath = ".//a[contains(@href, '/followers/')]")
    protected WebElement followers;

    @FindBy(xpath = ".//a[.=\"Профиль\"]")
    protected WebElement profileBtn;

    public final String URL_BASE;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        URL_BASE = driver.getCurrentUrl();
        wait("div", titleInstagram);
    }

    public void addFollowers(List<String> myFollowers) {
        btnClick(followers);
        wait("divs", ".//li[@class=\"_cx1ua\"]");
        String checker = "stringForChecking";
        WebElement el;
        while (true) {
            try {
                el = getDriver().findElement(By
                        .xpath("(.//span[@class = '_7k49n']/*[contains(text(), 'Подписаться')])[1]"));
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", el);
                WebElement element = getDriver()
                        .findElement(By.xpath("(.//a[@class=\"_4zhc5 notranslate _j7lfh\"])[1]"));
                if (! myFollowers.contains(element.getText()))
                    el.click();
                sleep(3);
                if (el.getText().equals("Подписаться")) {
                    getDriver().navigate().refresh();
                    addFollowers(myFollowers);
                }
                sleep(7);
            } catch (org.openqa.selenium.NoSuchElementException ex) {
//                gotoLastElemet(".//span[@class = '_7k49n']/button");
                List<WebElement> rowsFollowers = getDriver()
                        .findElements(By.xpath(".//span[@class = '_7k49n']/button"));
                WebElement lastElement = rowsFollowers.get(rowsFollowers.size()-1);
                ((JavascriptExecutor) getDriver())
                        .executeScript("arguments[0].scrollIntoView(true);", lastElement);
                sleep(1);
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
        Set<String> followers = new HashSet<>();
        List<WebElement> followersTmp = getDriver()
                .findElements(By.xpath(".//a[@class=\"_4zhc5 notranslate _j7lfh\"]"));
        WebElement lastElement = followersTmp.get(1);
        for (WebElement follower: followersTmp)
            followers.add(follower.getText());
        while (! checker.equals(lastElement.getText())) {
            checker = lastElement.getText();
            followersTmp = getDriver()
                    .findElements(By.xpath(".//a[@class=\"_4zhc5 notranslate _j7lfh\"]"));
            lastElement = followersTmp.get(followersTmp.size() - 1);
            for (int i = 1; i < 11; i++) {
                WebElement follower = followersTmp.get(followersTmp.size() - i);
                followers.add(follower.getText());
            }
            ((JavascriptExecutor) getDriver())
                    .executeScript("arguments[0].scrollIntoView(true);", lastElement);
            sleep(1);
        }
        return new ArrayList<>(followers);
    }
}
