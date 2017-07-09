package haulmont.appmanager.pages.references;

import haulmont.appmanager.pages.Main;
import haulmont.appmanager.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Mikhail on 04.07.2017.
 */
public class TypicalResolution extends Main {

    @FindBy(xpath = ".//div[@cuba-id=\"create\"][contains(@class, \"v-disabled\")]")
    public WebElement createBtn_disabled;

    @FindBy(xpath = ".//div[@cuba-id=\"create\"][contains(@class, \"v-button v-widget\")]")
    public WebElement createBtn_enabled;

    public TypicalResolution(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
