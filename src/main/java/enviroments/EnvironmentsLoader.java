package enviroments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.AssistanceMethods;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentsLoader {

    private static final Logger logger = LoggerFactory.getLogger(EnvironmentsLoader.class);


    // private instance, so that it can be accessed by only by getInstance() method
    private static EnvironmentsLoader environmentsLoader;
    static String environment = null;
    static Properties environmentProperties = null;



    //Get Instance -> The only place to initialize the object
    public static EnvironmentsLoader getInstance() {
        if (environmentsLoader == null) {
            //synchronized block to remove overhead
            synchronized (EnvironmentsLoader.class) {
                if (environmentsLoader == null) {
                    // if instance is null, initialize
                    environmentsLoader = new EnvironmentsLoader();
                }
            }
        }
        return environmentsLoader;
    }

    //Private Constructor to prevent initializing outside the class
    private EnvironmentsLoader() {
        //Set the env variable -->testing by default
        setEnvironment();
        //load all env property files
        loadEnvironmentProperties();
    }


    private void setEnvironment() {
        try {
            environment = System.getenv("DOMAIN");
            if (environment == null) {
                environment = "test";
            } else if (environment.isEmpty()) {
                environment = "test";
            }
        } catch (Exception e) {
            logger.error("Error in set the env: " + e.getMessage(), e);
        }
    }


    private void loadEnvironmentProperties() {

        FileReader domainPropertiesFile = null ;
        String localDirectory = System.getProperty("user.dir");

        //Read the property files
        try {
            System.out.println(localDirectory+environment);
            domainPropertiesFile = new FileReader(localDirectory + "/src/main/resources/" + environment + "/domain.properties");
        } catch (FileNotFoundException e) {
            logger.error("Error in load the local dir: {}", e.getMessage(), e);
        }

        //Load the files into the property variables
        environmentProperties = new Properties();

        try {
            environmentProperties.load(domainPropertiesFile);
        } catch (IOException e) {
            logger.error("Error in load the domain: " + e.getMessage(), e);
        }

    }

    public String getEnvPropValue(String key) {
        return environmentProperties.getProperty(key);
    }


}
