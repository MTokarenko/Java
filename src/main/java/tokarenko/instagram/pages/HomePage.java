package tokarenko.instagram.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tokarenko.AbstractPage;

import java.util.List;

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
                    el = getDriver().findElement(By.xpath("(.//span[@class = '_7k49n']/*[contains(text(), 'Подписаться')])[1]"));
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", el);
                    el.click();
                    sleep(3);
                    if (el.getText().equals("Подписаться")) {
//                        getDriver().manage().deleteAllCookies();
                        getDriver().navigate().refresh();
                        addFollowers();
                    }
                    sleep(7);
                } catch (org.openqa.selenium.NoSuchElementException ex) {
                    List<WebElement> rowsFollowers = getDriver().findElements(By.xpath(".//span[@class = '_7k49n']/button"));
                    WebElement lastElement = rowsFollowers.get(rowsFollowers.size()-1);
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", lastElement);
                    sleep(1);
                }

            }
        }
    }

    public void openProfile() {
        btnClick(profileBtn);
        wait("button", ".//button[.=\"Редактировать профиль\"]");
    }
}
