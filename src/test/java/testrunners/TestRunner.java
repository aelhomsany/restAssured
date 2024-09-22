package testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterTest;

@CucumberOptions(
        dryRun = false,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-report.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:lib/extentreports-cucumber-adapter.jar"
        },
        monochrome = true,
        features = {"src/test/resources/features"},
        glue = {"stepdefinitions"},
        tags = "@hs"
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterTest
    public void after_test() {

    }

}
