package enviroments;

public class EnvironmentsPicker {
private EnvironmentsLoader environmentsLoader;

public EnvironmentsPicker() {environmentsLoader = EnvironmentsLoader.getInstance();}


public String getRootURL(){return environmentsLoader.getEnvPropValue("ROOT_URL");}


}
