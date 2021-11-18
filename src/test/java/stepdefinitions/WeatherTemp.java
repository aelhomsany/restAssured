package stepdefinitions;


import base.Base;
import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.Assert;
import java.io.PrintStream;
import java.io.StringWriter;


public class WeatherTemp extends Base implements En   {

    // start captures the request
    StringWriter requestWriter = new StringWriter();
    PrintStream requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);


    public WeatherTemp(Base base ) {

        When("^send request to get \"([^\"]*)\" temperature$", (String query) -> {
            RestAssured.baseURI = "http://api.weatherstack.com/";
            RequestSpecification request = RestAssured.given()
                    .filter(new RequestLoggingFilter(requestCapture))
                    ;
            base.city = query;
            // send request
            Response response = request.queryParams("access_key", "fa59fed84dfe86fb7c06065ecb670c6a")
                    .queryParams("query",query)
                    .get("current")
                    ;
            base.response = response;
            System.out.println(requestWriter.toString());
            System.out.println(response.getBody().asString());

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
