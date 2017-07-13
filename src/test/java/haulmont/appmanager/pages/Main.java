package haulmont.appmanager.pages;


import com.google.common.collect.Lists;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.*;

import static tokarenko.haulmont.tezis.data.Data.TEZIS_BTN;
import static utils.Utils.sleep;


public class Main extends Page {

    @FindBy(xpath = TEZIS_BTN)
    protected WebElement tezisLogo;

    @FindBy(xpath = ".//span[@cuba-id=\"administration\"]")
    private WebElement administrationBTN;

    @FindBy(xpath = ".//span[@cuba-id=\"reference\"]")
    private WebElement referenceBTN;

    @FindBy(xpath = ".//span[@cuba-id=\"sec$User.browse\"]")
    private WebElement usersBtn;

    @FindBy(xpath = ".//span[@cuba-id=\"sec$Role.browse\"]")
    private WebElement rolesBtn;

    @FindBy(xpath = ".//span[@cuba-id=\"df$UserSubstitution.browse\"]")
    private WebElement substitutionsBtn;

    @FindBy(xpath = ".//div[@cuba-id=\"logoutButton\"]")
    private WebElement logoutBtn;

    @FindBy(xpath = ".//div[@cuba-id=\"windowClose\"]")
    private WebElement windowCloseBtn;

    @FindBy(xpath = ".//div[@cuba-id=\"windowCommit\"]")
    private WebElement windowCommitBtn;

    @FindBy(xpath = ".//span[@cuba-id=\"df$TypicalResolution.browse\"]")
    private WebElement typicalResolutions;

    @FindBy(xpath = ".//div[@cuba-id=\"create\"]")
    public WebElement createBtn;

    @FindBy(xpath = ".//div[@cuba-id=\"modeAction\"]")
    private WebElement filterMode;

//    @FindBy(xpath = ".//input[@class=\"v-filterselect-input\"]")
    @FindBy(xpath = ".//div[@cuba-id=\"search\"]/ancestor::div[3]/preceding-sibling::div//input[@class=\"v-filterselect-input\"]")
    public WebElement filterConditionInput;

    private String xPathFilterValue = ".//td[contains(@class, 'gwt-MenuItem')]/span[contains(text(), \"%s\")]";


    public Main(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public Main openUsersScreen() {
        try {
            btnClick(administrationBTN);
            usersBtn.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            btnClick(administrationBTN);
        }
        btnClick(usersBtn);
        wait("div", ".//div[@cuba-id=\"createPopupButton\"]");
        return this;
    }

    public Main openRolesScreen() {
        try {
            btnClick(administrationBTN);
            rolesBtn.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            btnClick(administrationBTN);
        }
        btnClick(rolesBtn);
        wait("div", ".//td[@cuba-id=\"tab_sec$Role.browse\"]");
        return this;
    }

    public Main openSubstitutionScreen() {
        btnClick(referenceBTN).btnClick(substitutionsBtn);
        wait("div", ".//td[@cuba-id=\"tab_df$UserSubstitution.browse\"]");
        return this;
    }

    public void showAllRowsInTable() {
        wait("div", ".//div[@cuba-id=\"tableSettings\"]");
        sleep(1);
        driver.findElement(By.xpath(".//div[@cuba-id=\"tableSettings\"]")).click();
        btnClick(".//div[@cuba-id=\"setMaxResults_0\"]");
        waiting.until(ExpectedConditions.textToBePresentInElementLocated
                (By.xpath(".//div[contains(@class, 'paging-status')][contains(text(), 'строк')]"), "строк"));
    }

    public List<String> getRowsFromTable(String rowNumber) {
        String fieldXpath = String.format
                (".//table[@class=\"v-table-table\"]//tr[contains(@class, 'v-table')]/td[%s]", rowNumber);
        List<String> users = new ArrayList<>();
        waiting.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(fieldXpath)));
        List<WebElement> elements = driver.findElements(By.xpath(fieldXpath));
        for (WebElement element : elements) {
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
            List<WebElement> refTmp = findElements(
                    ".//div[@class=\"popupContent\"]//span[@class=\"v-menubar-submenu-indicator\"]");
            for (WebElement el : refTmp) {
                el.click();
                List<WebElement> refWebEl = findElements(referencexPath);
                for (WebElement el1 : refWebEl) {
                    references.add(el1.getText());
                }
            }
        } catch (Throwable t) {
            List<WebElement> refWebEl = findElements(referencexPath);
            for (WebElement element : refWebEl) {
                references.add(element.getText());
            }
        }
        btnClick(referenceBTN);
        return new ArrayList<>(references);
    }

    private void findAndClickToElementFromMainMenu (String elementName, String listXPath) {
        for (WebElement reference : findElements(listXPath)) {
            if (elementName.equals(reference.getText())) {
                reference.click();
                return;
            }
        }
    }

    public Main openReference(String referenceName) {
        String referencexPath = ".//div[@class=\"popupContent\"]//span[@class=\"v-menubar-menuitem-caption\"]";
        Set<String> references = new HashSet<>();
        btnClick(referenceBTN);
        wait("divs", referencexPath);
        findAndClickToElementFromMainMenu(referenceName, referencexPath);
        for (WebElement submenu: findElements(
                ".//div[@class=\"popupContent\"]//span[@class=\"v-menubar-submenu-indicator\"]")) {
            submenu.click();
            findAndClickToElementFromMainMenu(referenceName, referencexPath);
        }
        return this;

    }

    public List<String> getRowsFromLongTable(String columnNumber) {
        int stringsCount = -1;
        showAllRowsInTable();
        String fieldXpath = String.format
                (".//table[@class=\"v-table-table\"]//tr[contains(@class, 'v-table')]/td[%s]", columnNumber);
        LinkedHashSet<String> usersTmp = new LinkedHashSet<>();
        while (stringsCount < usersTmp.size()) {
            stringsCount = usersTmp.size();
            waiting.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(fieldXpath)));
            sleep(1);
            List<WebElement> elements = driver.findElements(By.xpath(fieldXpath));
            for (WebElement element : elements) {
                usersTmp.add(element.getText());
            }
            WebElement lastElement = elements.get(elements.size() - 1);
            scrollTo(lastElement);

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

    public List getUsers() {
        if (!isCurrentScreen("tab_sec$User.browse")) {
            openUsersScreen();
        }
        List users = getRowsFromLongTable("3");
        return users;
    }

    public void logout() {
        btnClick(logoutBtn);
    }

    public Main relogin(String login, String pass) {
        String currentUser;
        try {
            currentUser = driver.findElement(By.xpath(".//div[@cuba-id=\"substitutedUserSelect\"]/input")).getAttribute("value");
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            currentUser = driver.findElement(By.xpath(".//div[@cuba-id=\"currentUserLabel\"]")).getText();
        }
        currentUser = currentUser.split(" ")[0];
        if (currentUser.equals("Administrator")) {
            currentUser = "admin";
        }
        if  (! currentUser.equals(login)) {
            Login loginPage = new Login(driver);
            logout();
            if (login.equals("admin")) {
                pass = "admin";
            }
            loginPage.login(login, pass);
        }
        return this;
    }


    public Main checkUser(String role) {
        List<String> users = getUsers();
        if (!users.contains(role)) {
            NewUser newUser = new NewUser(driver);
            newUser.createUser(role);
        }
        return this;
    }

    public Main openTypicalResolutionPage() {
        try {
            btnClick(referenceBTN);
            typicalResolutions.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            btnClick(referenceBTN);
        }
        btnClick(typicalResolutions);
        wait("div", ".//div[@cuba-id=\"create\"]");
        return this;
    }

    public Main checkUsers(List<String> roles) {
        NewUser newUser = new NewUser(driver);
        List<String> users = getUsers();
        for (String role : roles) {
            if (!users.contains(role)) {
                newUser.createUser(role);
            }
        }
        return this;
    }

    public Main deleteUser(String userName) {
        if (!isCurrentScreen("tab_sec$User.browse")) {
            openUsersScreen();
        }
        int stringsCount = -1;
        showAllRowsInTable();
        String fieldXpath = ".//table[@class=\"v-table-table\"]//tr[contains(@class, 'v-table')]/td[3]";
        LinkedHashSet<String> usersTmp = new LinkedHashSet<>();
        while (stringsCount < usersTmp.size()) {
            stringsCount = usersTmp.size();
            wait("divs",fieldXpath);
            sleep(1);
            List<WebElement> elements = findElements(fieldXpath);
            for (WebElement element : elements) {
                usersTmp.add(element.getText());
                if (element.getText().equals(userName)) {
                    element.click();
                    btnClick(".//div[@cuba-id=\"userTableRemoveBtn\"]");
                    btnClick(".//div[@cuba-id=\"optionDialog_ok\"]");
                    return this;
                }
            }
            WebElement lastElement = elements.get(elements.size() - 1);
            scrollTo(lastElement);
            lastElement.click();
        }
        return this;
    }

    public void altL() {
        new Actions(driver).keyDown(Keys.ALT).sendKeys(Keys.chord("L")).perform();
        wait("div", ".//td[@cuba-id=\"tab_df$Employee.browse\"]");
        Assert.assertTrue(isCurrentScreen("tab_df$Employee.browse"));
    }

    public void altM() {
        new Actions(driver).keyDown(Keys.ALT).sendKeys(Keys.chord("M")).perform();
        wait("div", ".//td[@cuba-id=\"tab_df$Company.browse\"]");
        Assert.assertTrue(isCurrentScreen("tab_df$Company.browse"));
    }

    public Main checkAdvancedFilter() {
        try {
            filterConditionInput.isDisplayed();
        } catch (NoSuchElementException exception) {
            filterMode.click();
        }
        return this;
    }

    public List<String> getAllFields() {
        List<String> fields = new ArrayList<>();
        btnClick(createBtn);
        wait("button", windowCommitBtn);
        for (WebElement el: findElements(".//div[@class=\"v-gridlayout-slot\"]")) {
            if (!Lists.newArrayList("*", "").contains(el.getText()))
                fields.add(el.getText());
        }
        btnClick(windowCloseBtn);
        return fields;
    }

    public Main checkStringInFilter(String field) {
        fieldInsert(filterConditionInput, field);
        Assert.assertTrue(findElement(String.format(xPathFilterValue, field)).isDisplayed());
        return this;
    }
}
