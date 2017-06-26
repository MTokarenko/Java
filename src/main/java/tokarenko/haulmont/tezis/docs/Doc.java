package tokarenko.haulmont.tezis.docs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tokarenko.haulmont.tezis.pages.MainPage;


public class Doc extends MainPage {

    private WebElement createDocDialogOkBtn;

    @FindBy(xpath = ".//span[@cuba-id=\"docflow\"]")
    private WebElement DOCUMENTS;

    @FindBy(xpath = ".//span[@cuba-id=\"docCreatorItem\"]")
    private WebElement createDocumentBtn;

    public Doc(WebDriver driver) {
        super(driver);
    }
    
    public void createDocument(String documentKind) {
        openDialogDocCreator();
//        chooseKind(documentKind);
//        btnClick(createDocDialogOkBtn);

    }

    private void chooseKind(String documentKind) {

    }

    private void openDialogDocCreator() {
        btnClick(DOCUMENTS).btnClick(createDocumentBtn);
        wait("div", ".//div[@cuba-id=\"docKindLookup\"]");
    }
}
