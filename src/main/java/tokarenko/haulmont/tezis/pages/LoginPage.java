package tokarenko.haulmont.tezis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import tokarenko.AbstractPage;

/**
 * Created by Mikhail on 20.05.2017.
 */
public class LoginPage extends AbstractPage {

    @FindBy(id = "s")
    protected WebElement userName;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


}
