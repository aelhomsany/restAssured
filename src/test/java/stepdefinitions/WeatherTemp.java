package stepdefinitions;


import base.Base;
import io.cucumber.java8.En;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import request.Request;



public class WeatherTemp extends Base implements En   {


    public WeatherTemp(Base base ) {

        When("^send request to get \"([^\"]*)\" temperature$", (String query) -> {

            JSONObject queryParams = new JSONObject();
            queryParams.put("access_key", "fa59fed84dfe86fb7c06065ecb670c6a");
            queryParams.put("query", query);

            Request.Builder requestBuilder = new Request.Builder("http://api.weatherstack.com/", Method.GET);
            requestBuilder.queryParameters(queryParams).path("current");
            Request request = requestBuilder.build();
            base.response = request.send();

            base.city = query;
            System.out.println(request.getLogs());
        });
        Then("^status code is \"([^\"]*)\"$", (Integer statusCode) -> {
         int       responseStatusCode  = base.response.getStatusCode();
            Assert.assertEquals(Integer.parseInt(String.valueOf(statusCode)),responseStatusCode);
        });

        And("^temperatures printed out$", () -> {
            int temperature = base.response.jsonPath().get("current.temperature");
            System.out.println("Temperature in " + base.city +": "+ temperature);

        });


    }
}
