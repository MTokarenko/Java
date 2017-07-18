package haulmont.tests;


import com.haulmont.masquerade.components.Button;
import haulmont.appmanager.pages.DemoLoginWindow;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.haulmont.masquerade.Components._$;

/**
 * Created by Mikhail on 30.06.2017.
 */
public class ReferenceTest extends TestBase {


    @Test
    public void testSimpleUserVisible() {
        List<String> ignoredReferences = Arrays.asList("Замещение пользователей", "Приоритеты");
        List<String> referenceAdmin = app.getMainPage().getReferences();
        referenceAdmin.removeAll(ignoredReferences);
        app.getMainPage()
                .openUsersScreen()
                .checkUser("SimpleUser")
                .relogin("SimpleUser", "123");
        List<String> referenceSimpleUser = app.getMainPage().getReferences();
        Assert.assertTrue(referenceAdmin.size() == referenceSimpleUser.size()
                && referenceAdmin.containsAll(referenceSimpleUser));
    }

    @Test(priority = 1)
    public void testTypicalResolutionCreation() {

//        Button button = _$(Button.class, "loginBtn");
//        button.click();
//        DemoLoginWindow demoLoginWindow = _$(DemoLoginWindow.class);
//        demoLoginWindow = _$(DemoLoginWindow.class);

        List<String> roles = Arrays.asList("Administrator", "doc_approver", "ReferenceEditor");
        app.getMainPage().checkUsers(roles);
        for (String role : roles) {
            app.getMainPage()
                    .relogin(role, "123")
                    .openTypicalResolutionPage();
            Assert.assertTrue(app.getTypicalResolution().createBtn_enabled.isEnabled(),
                    String.format("Create button disabled for user with role %s", role));
        }
    }

    @Test(enabled = false)
    public void testTypicalResolutionEdition() {
        List<String> roles = Arrays.asList("admin", "doc_approver", "ReferenceEditor");
        app.getMainPage().checkUsers(roles);
        for (String role : roles) {
            app.getMainPage()
                    .relogin(role, "123")
                    .openTypicalResolutionPage();
            if (role.equals("admin")) {

            }
        }
    }

    @Test(priority = 1)
    public void testReferencesVisibleForUsers() {
        List<String> roles = Arrays.asList("Administrator", "UserSubstitutionEditor", "SimpleUser");
        List<String> references = Arrays.asList("Замещение пользователей");
        app.getMainPage().checkUsers(roles);
        for (String role : roles) {
            app.getMainPage()
                    .relogin(role, "123");
            List<String> currentRefs = app.getMainPage().getReferences();
            if (!role.equals("SimpleUser")) {
                Assert.assertTrue(currentRefs.containsAll(references));
            } else {
                Assert.assertFalse(currentRefs.containsAll(references));
            }
        }
    }

    @Test(priority = 3)
    public void testEmployeeAfterUserCreation() {
        app.getNewUser().createUser("AppIntegrationRole");
        app.getMainPage().deleteUser("AppIntegrationRole");
    }

    @Test(priority = 4)
    public void testHotKeys() {
        app.getMainPage().altL();
        app.getMainPage().altM();
    }


    @Test(priority = 2)
    public void testFiltersInReferences() {
        List<String> allFields;
        List<String> references = Arrays.asList( "Наши организации", "Сотрудники",
                "Должности", "Юридические лица", "Физические лица", "Валюты", "Банки", "Регионы банков");
//        List<String> references = Arrays.asList( "Банки");
        for (String reference: references) {
            app.getMainPage()
                    .openReference(reference)
                    .checkAdvancedFilter();
            allFields = app.getMainPage().getAllFields();
            for (String field: allFields) {
                app.getMainPage().checkStringInFilter(field);
            }
            app.getMainPage().closeCurrentTab();
        }
    }

}
