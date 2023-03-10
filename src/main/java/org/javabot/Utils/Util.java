package org.javabot.Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Util {

    public static String getToken() {
        Properties props = new Properties();

        try (FileInputStream file = new FileInputStream("src/main/resources/application.properties")) {
            props.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return props.getProperty("TOKEN");
    }
}
