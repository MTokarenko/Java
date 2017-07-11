package haulmont.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static utils.Utils.sleep;

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

    @Test
    public void testTypicalResolutionCreation() {
        List<String> roles = Arrays.asList("admin", "doc_approver", "ReferenceEditor");
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

    @Test
    public void testReferencesVisibleForUsers() {
        List<String> roles = Arrays.asList("admin", "UserSubstitutionEditor", "SimpleUser");
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

    @Test
    public void testEmployeeAfterUserCreation() {
        app.getNewUser().createUser("AppIntegrationRole");
        app.getMainPage().deleteUser("AppIntegrationRole");
    }

    @Test
    public void testHotKeys() {
        app.getMainPage().altL();
        app.getMainPage().altM();
    }


    @Test
    public void testFiltersInReferences() {
        List<String> references = Arrays.asList("Группы пользователей", "Наши организации", "Сотрудники",
                "Должности", "Контрагенты", "Банки и валюты");
        app.getMainPage()
                .openReference("Банки")
                .wait("div", app.getMainPage().createBtn);

        System.out.println(app.getMainPage().filter.getText());
    }

}
