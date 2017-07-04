/*package haulmont.appmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Prop {

    Logger logger = LoggerFactory.getLogger(Prop.class);
    private static Prop p;
    private static Properties properties;

    public static synchronized Prop getPropInstance() {
        if (null == p) {
            p = new Prop();
        }
        return p;
    }

    public Prop() {
        try {
            String sConfig = System.getProperty("ConfigFile", "config/application.properties");
            InputStream in = Prop.class.getClassLoader().getResourceAsStream(sConfig);
            if (null == in) {
                in = new FileInputStream(sConfig);
            }
            properties = new Properties();
            properties.load(in);
        } catch (Exception e) {
            logger.error("Ошибка загрузки конфига!", e);
        }
    }

    private String getProp(String name) {
        String s = getProp().getProperty(name, "");
        if (s.isEmpty()) {
            logger.info("Не найденно значение {} в конфиге!", name);
        }
        return s.trim();
    }

    public static String get(String prop) {
        return getPropInstance().getProp(prop);
    }

    public static String get(String prop, String defaultValue) {
        String _s = getPropInstance().getProp(prop);
        return _s.isEmpty() ? defaultValue : _s;
    }

    public static Properties getProperties(){
        return props;
    }

}*/
