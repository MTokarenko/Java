package tokarenko.haulmont.tezis.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tokarenko.AbstractPage;
import static tokarenko.haulmont.tezis.data.Data.*;


public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@cuba-id='loginField']")
    protected WebElement userName;

    @FindBy(xpath = "//input[@cuba-id='passwordField']")
    protected WebElement userPass;

    @FindBy(xpath = "//div[@cuba-id='loginSubmitButton']")
    protected WebElement enterButton;

    @FindBy(xpath = "//div[@cuba-id=\"logoutButton\"]")
    protected WebElement logoutBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait("button", enterButton);
    }

    public void login(String login, String pass) {
        fieldInsert(userName, login);
        fieldInsert(userPass, pass);
        enterButton.click();
        wait("div", TEZIS_BTN);
    }

    public void relogin(String login, String pass) {
        btnClick(logoutBtn);
        login(login, pass);
    }
}
