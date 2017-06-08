package tokarenko.haulmont.tezis.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import tokarenko.AbstractPage;
import static utils.Utils.sleep;
import static tokarenko.haulmont.tezis.data.Data.*;

import java.util.ArrayList;
import java.util.List;


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

    public void showAllRowsStrings() {
        wait("button", ".//div[@cuba-id=\"tableSettings\"]");
        sleep(1);
        getDriver().findElement(By.xpath(".//div[@cuba-id=\"tableSettings\"]")).click();
        btnClick(".//div[@cuba-id=\"setMaxResults_0\"]");
        waiting.until(ExpectedConditions.textToBePresentInElementLocated
                (By.xpath(".//div[contains(@class, 'paging-status')][contains(text(), 'строк')]"), "строк"));
    }

    public List<String> getRowFromTable(String rowNumber) {
        String fieldXpath = String.format
                (".//table[@class=\"v-table-table\"]//tr[contains(@class, 'v-table')]/td[%s]", rowNumber);
        List<String> users = new ArrayList<>();
        showAllRowsStrings();
        List <WebElement> elements = getDriver().findElements(By.xpath(fieldXpath));
        for (WebElement element: elements) {
            users.add(element.getText());
        }
        return users;
    }
}