package haulmont.tests;

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
        try {
            List<String> referenceAdmin = app.getMainPage().getReferences();
            referenceAdmin.removeAll(ignoredReferences);
            NewUserScreen newUser = new NewUserScreen(app.driver);
            app.getMainPage().openUsersScreen();
            newUser.createUser("SimpleUser");
            app.getMainPage().logout();
            app.getLogin().login("SimpleUser", "123");
            List<String> referenceSimpleUser = app.getMainPage().getReferences();
            if(referenceAdmin.size() == referenceSimpleUser.size() && referenceAdmin.containsAll(referenceSimpleUser)){
                System.out.println("OK");
            }else System.out.println("FALSE");
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
