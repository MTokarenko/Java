package haulmont.appmanager.pages;

import com.haulmont.masquerade.Wire;
import com.haulmont.masquerade.base.Composite;
import com.haulmont.masquerade.components.TextField;

public class DemoLoginWindow extends Composite<DemoLoginWindow> {

    @Wire
    public TextField loginField;
}