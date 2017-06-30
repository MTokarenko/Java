package tokarenko.haulmont.tezis.pages;


import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Wire;
import com.haulmont.masquerade.components.Untyped;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import tokarenko.AbstractPage;

import static com.codeborne.selenide.Selenide.$;
import static utils.Utils.sleep;
import static tokarenko.haulmont.tezis.data.Data.*;

import java.util.*;


public class MainPage extends AbstractPage {
    @FindBy(xpath = TEZIS_BTN)
    protected WebElement tezisLogo;

    @FindBy(xpath = "//span[@cuba-id=\"administration\"]")
    protected WebElement administrationBTN;

    @FindBy(xpath = "//span[@cuba-id=\"reference\"]")
    protected WebElement referenceBTN;

    @FindBy(xpath = "//span[@cuba-id=\"sec$User.browse\"]")
    protected WebElement usersBtn;



    @Wire(path = "sec$User.browse")
    protected Untyped userBtnMasq;

    @FindBy(xpath = "//span[@cuba-id=\"sec$Role.browse\"]")
    protected WebElement rolesBtn;

    @FindBy(xpath = "//span[@cuba-id=\"df$UserSubstitution.browse\"]")
    protected WebElement substitutionsBtn;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait("div", tezisLogo);
    }

    public AbstractPage openUsersScreen() {
        try {
            btnClick(administrationBTN);
            usersBtn.isDisplayed();
        }catch (org.openqa.selenium.NoSuchElementException ex) {
            btnClick(administrationBTN);
        }
        btnClick(usersBtn);
        return this;
    }

    public AbstractPage openRolesScreen() {
        btnClick(administrationBTN).btnClick(rolesBtn);
        wait("div", ".//td[@cuba-id=\"tab_sec$Role.browse\"]");
        return this;
    }

    public AbstractPage openSubstitutionScreen() {
        btnClick(referenceBTN).btnClick(substitutionsBtn);
        wait("div", ".//td[@cuba-id=\"tab_df$UserSubstitution.browse\"]");
        return this;
    }

    public void showAllRowsStrings() {
        wait("div", ".//div[@cuba-id=\"tableSettings\"]");
        sleep(1);
        getDriver().findElement(By.xpath(".//div[@cuba-id=\"tableSettings\"]")).click();
        btnClick(".//div[@cuba-id=\"setMaxResults_0\"]");
        waiting.until(ExpectedConditions.textToBePresentInElementLocated
                (By.xpath(".//div[contains(@class, 'paging-status')][contains(text(), 'строк')]"), "строк"));
    }

    public List<String> getRowsFromTable(String rowNumber) {
        String fieldXpath = String.format
                (".//table[@class=\"v-table-table\"]//tr[contains(@class, 'v-table')]/td[%s]", rowNumber);
        List<String> users = new ArrayList<>();
        waiting.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(fieldXpath)));
        List <WebElement> elements = getDriver().findElements(By.xpath(fieldXpath));
        for (WebElement element: elements) {
            users.add(element.getText());
        }
        return users;
    }

    public List<String> getReferences() {
        String referencexPath = ".//div[@class=\"popupContent\"]//span[@class=\"v-menubar-menuitem-caption\"]";
        Set<String> references = new HashSet<>();
        btnClick(referenceBTN);
        wait("divs", referencexPath);
        try {
            List<WebElement> refTmp = findElements(".//div[@class=\"popupContent\"]//span[@class=\"v-menubar-submenu-indicator\"]");
            for (WebElement el: refTmp ) {
                el.click();
                List<WebElement> refWebEl = findElements(referencexPath);
                for (WebElement el1: refWebEl) {
                    references.add(el1.getText());
                }
            }
        }catch (Throwable t){
            List<WebElement> refWebEl = findElements(referencexPath);
            for (WebElement element: refWebEl) {
                references.add(element.getText());
            }
        }
        btnClick(referenceBTN);
        return new ArrayList<>(references);
    }

    public List<String> getRowsFromLongTable(String columnNumber) {
        int stringsCount = -1;
        showAllRowsStrings();
        String fieldXpath = String.format
                (".//table[@class=\"v-table-table\"]//tr[contains(@class, 'v-table')]/td[%s]", columnNumber);
        LinkedHashSet<String> usersTmp = new LinkedHashSet<>();
        while (stringsCount < usersTmp.size()) {
            stringsCount = usersTmp.size();
            waiting.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(fieldXpath)));
            sleep(1);
            List<WebElement> elements = getDriver().findElements(By.xpath(fieldXpath));
            for (WebElement element : elements) {
                usersTmp.add(element.getText());
            }
            WebElement lastElement = elements.get(elements.size()-1);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", lastElement);

            lastElement.click();
        }
        List<String> users = new ArrayList<>(usersTmp);
        return users;
    }

    public List getRoles() {
        openRolesScreen();
        List roles = getRowsFromTable("1");
        btnClick(".//div[contains(text(), 'Роли')]/following-sibling::span");
        return roles;
    }

    public List getUsers(){
        openUsersScreen();
        List users = getRowsFromLongTable("3");
        btnClick(".//div[contains(text(), 'Пользователи')]/following-sibling::span");
        return users;
    }
}
