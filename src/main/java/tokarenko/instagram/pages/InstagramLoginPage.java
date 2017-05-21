package tokarenko.instagram.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tokarenko.AbstractPage;


public class InstagramLoginPage extends AbstractPage {

    @FindBy(xpath = ".//button[contains(text(), 'Войти через Facebook')]")
    protected WebElement loginFromFacebookButton;

    public InstagramLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(loginFromFacebookButton));
    }

    public void click_loginFromFacebookButton() {
        loginFromFacebookButton.click();
    }

}
