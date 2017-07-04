package haulmont.tests;

import org.junit.Assert;
import org.junit.Test;

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
        app.getMainPage().openUsersScreen();
        app.getNewUser().createUser("SimpleUser");
        app.getMainPage().relogin("SimpleUser", "123");
        List<String> referenceSimpleUser = app.getMainPage().getReferences();
        Assert.assertTrue(referenceAdmin.size() == referenceSimpleUser.size()
                && referenceAdmin.containsAll(referenceSimpleUser));
    }

    @Test
    public void testTypicalResolutionCreation() {
        List<String> roles = Arrays.asList("admin", "doc_approver", "ReferenceEditor", "doc_initiator");
        app.getMainPage().checkUsers(roles);
        for (String role: roles) {
            app.getMainPage()
                    .relogin(role, "123")
                    .openTypicalResolutionPage();
            Assert.assertTrue(String.format("Create button disabled for user with role %s", role),
                    app.getTypicalResolution().createBtn_enabled.isEnabled());
        }
    }

}
