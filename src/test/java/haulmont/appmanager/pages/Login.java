package haulmont.appmanager.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static tokarenko.haulmont.tezis.data.Data.TEZIS_BTN;


public class Login extends Main {

    @FindBy(xpath = "//input[@cuba-id='loginField']")
    protected WebElement userName;

    @FindBy(xpath = "//input[@cuba-id='passwordField']")
    protected WebElement userPass;

    @FindBy(xpath = "//div[@cuba-id='loginSubmitButton']")
    protected WebElement enterButton;

    public Login(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void login(String login, String pass) {
        fieldInsert(userName, login);
        fieldInsert(userPass, pass);
        enterButton.click();
        wait("div", TEZIS_BTN);
    }

}
