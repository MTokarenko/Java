package tokarenko.haulmont.tezis.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tokarenko.AbstractPage;
import static tokarenko.haulmont.tezis.data.Data.*;

public class MainPage extends AbstractPage {

    @FindBy(xpath = TEZIS_BTN)
    protected WebElement tezisLogo;

    @FindBy(xpath = "//span[@cuba-id=\"administration\"]")
    protected WebElement administrationBtn;

    @FindBy(xpath = "//span[@cuba-id=\"sec$User.browse\"]")
    protected WebElement usersBtn;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait("div", tezisLogo);
    }

    public AbstractPage openUsersScreen() {
        btnClick(administrationBtn).btnClick(usersBtn);
        return this;
    }
}
