package haulmont.appmanager.pages.selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Created by Mikhail on 16.07.2017.
 */


public class SelLogin {

    @FindBy(xpath = "//input[@cuba-id='loginField']")
    public SelenideElement loginField;

    WebDriver driver;

    public SelLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, SelLogin.class);
    }

}
