package haulmont;


import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tokarenko.haulmont.tezis.docs.Doc;

public class CreateDocumentTest extends BaseTest {

//    public CreateDocumentTest(WebDriver driver) {
//        super(driver);
//    }

    private WebElement createDocDialogOkBtn;

    @FindBy(xpath = ".//span[@cuba-id=\"docflow\"]")
    private WebElement DOCUMENTS;

    @FindBy(xpath = ".//span[@cuba-id=\"docCreatorItem\"]")
    private WebElement createDocumentBtn;

    @Test
    public void createDocument(String documentKind) {

    }

    private void chooseKind(String documentKind) {

    }

}
