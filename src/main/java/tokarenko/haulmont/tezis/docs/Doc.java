package tokarenko.haulmont.tezis.docs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tokarenko.haulmont.tezis.pages.MainPage;


public class Doc extends MainPage {

    @FindBy(xpath = ".//div[@cuba-id=\"windowCommit\"]")
    private WebElement docCreationDialogOkBtn;

    @FindBy(xpath = ".//span[@cuba-id=\"docflow\"]")
    private WebElement DOCUMENTS;

    @FindBy(xpath = ".//span[@cuba-id=\"docCreatorItem\"]")
    private WebElement createDocumentBtn;

    @FindBy(xpath = ".//div[@cuba-id=\"docKindLookup\"]/input")
    private WebElement docKindLookupField;

    public Doc(WebDriver driver) {
        super(driver);
    }
    
    public void createDocument(String documentKind) {
        openDialogDocCreator();
        chooseKind(documentKind);
        btnClick(docCreationDialogOkBtn);
        wait("div", ".//div[@cuba-id=\"WebLabel\"]");
    }

    private void chooseKind(String documentKind) {
        fieldInsert(docKindLookupField, documentKind);
        btnClick(String.format(".//div[@class=\"popupContent\"]//span[.=\"%s\"]", documentKind));
    }

    private void openDialogDocCreator() {
        btnClick(DOCUMENTS).btnClick(createDocumentBtn);
        wait("div", ".//div[@cuba-id=\"docKindLookup\"]");
    }
}
