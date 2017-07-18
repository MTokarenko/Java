package haulmont.appmanager.pages.selenide;


import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;


/**
 * Created by Mikhail on 17.07.2017.
 */
public class SelenidePageFactory extends PageFactory {

    public static void initElements(FieldDecorator decorator, Object page) {
        Class<?> proxyIn = page.getClass();
        while (proxyIn != Object.class) {
            proxyFields(decorator, page, proxyIn);
            proxyIn = proxyIn.getSuperclass();
        }
    }

    private static void proxyFields(FieldDecorator decorator, Object page, Class<?> proxyIn) {
        Field[] fields = proxyIn.getDeclaredFields();
        for (Field field : fields) {
            if(isInitialized(page, field)){
                continue;
            }
            Object value = decorator.decorate(page.getClass().getClassLoader(), field);
            if (value != null) {
                try {
                    field.setAccessible(true);
                    field.set(page, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static boolean isInitialized(Object page, Field field){
        try {
            field.setAccessible(true);
            return field.get(page) != null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
