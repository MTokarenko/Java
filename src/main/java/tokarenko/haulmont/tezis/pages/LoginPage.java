package tokarenko.haulmont.tezis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import tokarenko.AbstractPage;


public class LoginPage extends AbstractPage {

    @FindBy(id = "s")
    protected WebElement userName;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


}
