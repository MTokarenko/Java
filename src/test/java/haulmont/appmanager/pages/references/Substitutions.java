package haulmont.appmanager.pages.references;


import haulmont.appmanager.pages.Main;
import haulmont.appmanager.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class Substitutions extends Main {



    @FindBy(xpath = ".//div[@cuba-id=\"user\"]/input")
    private WebElement userField;

    @FindBy(xpath = ".//div[@cuba-id=\"userSubstitution\"]/input")
    private WebElement subsField;

    @FindBy(xpath = ".//div[@cuba-id=\"search\"]/ancestor::div[3]/preceding-sibling::div//input[@class=\"v-filterselect-input\"]")
    private WebElement filterField;

    @FindBy(xpath = ".//div[@cuba-id=\"WebLookupPickerField_ds\"]/input")
    private WebElement filterLookupPickerField;

    @FindBy(xpath = ".//div[@cuba-id=\"create\"]")
    private WebElement createBTN;


    public Substitutions(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List getSubstitutionsForUser(String user) {
        fieldInsert(filterField, "Пользователь");
        btnClick("/td[co./ntains(@class, 'gwt-MenuItem')]/span[contains(text(), 'Пользователь')]");
        fieldInsert(filterLookupPickerField, user);
        filterLookupPickerField.sendKeys(Keys.ARROW_DOWN);
        filterLookupPickerField.sendKeys(Keys.ENTER);
        btnClick(".//div[@cuba-id=\"search\"]");
        List<String> subs = getRowsFromLongTable("3");
        return  subs;
    }

    public void createSubstitutions() throws InterruptedException {
        List<String> currentAdminSubs = getSubstitutionsForUser("Administrator");
        List<String> usersForSubs = getUsersForSubs();
        usersForSubs.removeAll(currentAdminSubs);
        for (String user: usersForSubs) {
            Thread.sleep(500);
            btnClick(createBtn);
            fieldInsert(userField, "Administrator");
            userField.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            userField.sendKeys(Keys.ENTER);
            fieldInsert(subsField, user);
            subsField.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            subsField.sendKeys(Keys.ENTER);
            btnClick(".//div[@cuba-id=\"windowCommit\"]");
        }
    }

    private List<String> getUsersForSubs() {
        List<String> roles = getRoles();
        List<String> users = getUsers();
        List<String> usersForSubs = new ArrayList<String>();
        for(String user: users) {
            if(roles.contains(user)) {
                usersForSubs.add(user);
            }
        }
        return usersForSubs;
    }

}
