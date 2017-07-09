package haulmont.appmanager.pages.references;

import haulmont.appmanager.pages.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Mikhail on 09.07.2017.
 */
public class Employees extends Main {

    @FindBy(xpath = ".//div[@cuba-id=\"create\"]")
    public WebElement createBtn;

    public Employees(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
