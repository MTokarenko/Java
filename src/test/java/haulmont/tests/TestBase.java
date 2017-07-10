package haulmont.tests;


import haulmont.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;



public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeClass
    public void setUp() {
        app.init();
    }

    @BeforeMethod
    public void reloginToAdmin() {
        app.getMainPage().relogin("admin", "admin");
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }

}
