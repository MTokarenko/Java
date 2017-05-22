package tokarenko.instagram.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tokarenko.AbstractPage;

import static org.junit.Assert.assertTrue;


public class FaceBookLoginPage extends AbstractPage {

    @FindBy(id = "email")
    protected WebElement userName;

    @FindBy(id = "pass")
    protected WebElement userPass;

    @FindBy(id = "loginbutton")
    protected WebElement loginButton;

    @FindBy(xpath = ".//input[@placeholder = 'Поиск']")
    protected WebElement search;



    public FaceBookLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }

    public void login(String login, String pass) {
        this.userName.sendKeys(login);
        this.userPass.sendKeys(pass);
        this.loginButton.click();

        wait.until(ExpectedConditions.visibilityOf(search));
        assertTrue("title should be 'Instagram'", getDriver()
                .getTitle().equals("Instagram"));
    }

}
