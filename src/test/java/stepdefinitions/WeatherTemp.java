package stepdefinitions;


import base.Base;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import request.AssistanceMethods;
import request.Request;


public class WeatherTemp extends Base implements En {


    public WeatherTemp(Base base) {


        When("^send request to get temperature$", ( DataTable datatable) -> {
            JSONObject queryParams = base.assistanceMethods.convertDataTableToJson(datatable);

            Request.Builder requestBuilder = new Request.Builder(base.environmentsPicker.getRootURL(), Method.GET);
            requestBuilder.queryParameters(queryParams).path(base.routes.CURRENT);
            Request request = requestBuilder.build();
            base.response = request.send();

            base.city = queryParams.get("query").toString();
            System.out.println(request.getLogs());

        });
        Then("^status code is \"([^\"]*)\"$", (Integer statusCode) -> {
            int responseStatusCode = base.response.getStatusCode();
            Assert.assertEquals(Integer.parseInt(String.valueOf(statusCode)), responseStatusCode);
        });

        And("^temperatures printed out$", () -> {
            int temperature = base.response.jsonPath().get("current.temperature");
            System.out.println("Temperature in " + base.city + ": " + temperature);

        });


    }
}
