package base;

import enviroments.EnvironmentsPicker;
import io.restassured.response.Response;
import request.AssistanceMethods;
import request.Routes;


public class Base {

  public   Routes routes = new Routes();
  public AssistanceMethods assistanceMethods = new AssistanceMethods();

  public EnvironmentsPicker environmentsPicker = new EnvironmentsPicker();
    // general Objects
    public Response response;
    public String city;


}
