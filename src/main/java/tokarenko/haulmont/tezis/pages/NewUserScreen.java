package tokarenko.haulmont.tezis.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static utils.Utils.sleep;

public class NewUserScreen extends MainPage {

    @FindBy(xpath = ".//input[@cuba-id=\"login\"]")
    protected WebElement login;

    @FindBy(xpath = ".//input[@cuba-id=\"passw\"]")
    protected WebElement password;

    @FindBy(xpath = ".//input[@cuba-id=\"confirmPassw\"]")
    protected WebElement confirmPassword;

    @FindBy(xpath = ".//input[@cuba-id=\"lastName\"]")
    protected WebElement surname;

    @FindBy(xpath = ".//input[@cuba-id=\"email\"]")
    protected WebElement mail;

    @FindBy(xpath = ".//span[@cuba-id=\"sendWelcomeEmail\"]/input")
    protected WebElement sendWelcomeEmail;

    @FindBy(xpath = ".//div[@cuba-id=\"lookup\"]")
    protected WebElement groupBtn;

    @FindBy(xpath = ".//span[@cuba-id=\"changePasswordAtNextLogon\"]/input")
    protected WebElement changePasswordAtNextEnter;

    @FindBy(xpath = ".//div[@cuba-id=\"rolesTableAddBtn\"]")
    protected WebElement rolesBtn;

    TreeSet<String> rejectedGroups = new TreeSet<String>(Arrays.asList("Administrators", "Archive access",
            "Archivist", "Department Chief", "doc_publisher", "meetingdoc_ceator", "PortalIntegrationRole",
            "schedule_task_creator", "SimpleUser", "SubdivisionChief", "UserSubstitutionEditor"));

    WebDriver driver;
    Boolean roleChecker = Boolean.TRUE;

    public NewUserScreen(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        sleep(2);
    }

    public void createUsers(String email) {
        List<String> currentUsers = getRowFromTable("3");
        btnClick(".//div[@cuba-id=\"createPopupButton\"]");
        btnClick(".//div[@cuba-id=\"create\"]");
        btnClick(rolesBtn);
        sleep(2);
        List<WebElement> rolesElements = driver.findElements(By.xpath(
                ".//table[@class=\"v-table-table\"]//tr[contains(@class, 'v-table')]/td[1]"));
        List<String> roles = new ArrayList<>();
        for (WebElement role : rolesElements) {
            String roleText = role.getText();
            roles.add(roleText);
        }
        roles.removeAll(rejectedGroups);
        for (String roleText : roles) {
            if (!currentUsers.contains(roleText)) {
                WebElement role = driver.findElement(By.xpath(String.format(".//div[text() = \"%s\"]", roleText)));
                role.click();
                btnClick(".//div[@cuba-id=\"selectButton\"]");
                login.sendKeys(roleText);
                password.sendKeys("123");
                confirmPassword.sendKeys("123");
                surname.sendKeys(roleText);
                mail.sendKeys(email);
                sendWelcomeEmail.sendKeys(Keys.SPACE);
                groupBtn.click();
                btnClick(".//span[contains(text(), 'Ограниченный доступ')]");
                sleep(1);
                btnClick(".//div[@cuba-id=\"selectButton\"]");
                changePasswordAtNextEnter.sendKeys(Keys.SPACE);
                btnClick(".//div[@cuba-id=\"windowCommit\"]");
                btnClick(".//div[@cuba-id=\"optionDialog_yes\"]");
                sleep(2);
                btnClick(".//div[@cuba-id=\"windowCommit\"]");
                sleep(1);
                btnClick(".//div[@cuba-id=\"createPopupButton\"]");
                btnClick(".//div[@cuba-id=\"create\"]");
                btnClick(rolesBtn);
                sleep(2);
            }
        }
    }
}

