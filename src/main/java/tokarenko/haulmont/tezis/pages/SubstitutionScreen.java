package tokarenko.haulmont.tezis.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SubstitutionScreen extends MainPage {

    @FindBy(xpath = "//span[@cuba-id=\"administration\"]")
    private WebElement createBtn;

    public SubstitutionScreen(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public List getSubstitutionsForUser(String user) {
        List<String> subs = new ArrayList<>();


        return  subs;
    }

    public void createSubstitutions() {
        List<String> currentAdminSubs = getSubstitutionsForUser("Administrator");
        List<String> usersForSubs = getUsersForSubs();
        usersForSubs.removeAll(currentAdminSubs);
        for (String user: usersForSubs) {
            btnClick(createBtn);
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
