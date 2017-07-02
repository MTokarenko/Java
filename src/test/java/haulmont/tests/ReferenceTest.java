package haulmont.tests;

import org.junit.Assert;
import org.junit.Test;
import tokarenko.haulmont.tezis.pages.MainPage;
import tokarenko.haulmont.tezis.pages.NewUserScreen;

import java.util.Arrays;
import java.util.List;

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
        app.getMainPage().logout();
        app.getLogin().login("SimpleUser", "123");
        List<String> referenceSimpleUser = app.getMainPage().getReferences();
        Assert.assertTrue(referenceAdmin.size() == referenceSimpleUser.size()
                && referenceAdmin.containsAll(referenceSimpleUser));
    }

}
