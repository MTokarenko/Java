package haulmont.tests;


import haulmont.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;



public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeClass
    public void setUp() {
        app.init();
    }

    @BeforeMethod
    public void reloginToAdmin() {
        app.getMainPage()
                .relogin("admin", "admin")
                .closeCurrentTab();
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }

}
