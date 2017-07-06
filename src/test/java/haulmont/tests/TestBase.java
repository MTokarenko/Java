package haulmont.tests;


import haulmont.appmanager.ApplicationManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

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
