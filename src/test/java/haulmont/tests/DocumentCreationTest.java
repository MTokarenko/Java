package haulmont.tests;


import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tokarenko.haulmont.tezis.docs.Doc;

import static utils.Utils.sleep;

public class DocumentCreationTest extends TestBase {

    private WebElement createDocDialogOkBtn;

    @FindBy(xpath = ".//span[@cuba-id=\"docflow\"]")
    private WebElement DOCUMENTS;

    @FindBy(xpath = ".//span[@cuba-id=\"docCreatorItem\"]")
    private WebElement createDocumentBtn;

    @Test
    public void testCreationSimpleDoc() {
        try {
            Doc doc = new Doc(app.driver);
            doc.createDocument("Письмо");
            doc.fillInputs();
            sleep(2);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
