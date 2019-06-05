package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Resources {


    public static String defaultRequestWarning() {
        String warningText = "Error - Please check entered information";
        return warningText;
    }

    public static String defaultEnvWarning() {
        String warningText = "Error - Please check env.properties file";
        return warningText;
    }

    public static String envPropertyFilePath() {
        String envPropertyFilePath = "./src/env.properties";
        return envPropertyFilePath;
    }

    public static String getEnvValue() throws IOException {
        String serviceURL = null;
        String envValue = System.getProperty("urlConfig");
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(Resources.envPropertyFilePath());
        prop.load(fis);
        if ("GOOGLE".equals(envValue)) {
            serviceURL = (String) prop.get("ENDPOINT_GOOGLE");
        } else if ("SS".equals(envValue)) {
            serviceURL = (String) prop.get("ENDPOINT_SS");
        } else if ("OOGE0".equals(envValue)) {
            serviceURL = (String) prop.get("ENDPOINT_OOGE0");
        } else {
            System.out.println(Resources.defaultEnvWarning());
        }
        return serviceURL;
    }
}
