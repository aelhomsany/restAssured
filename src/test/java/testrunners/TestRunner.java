package testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        dryRun = false,
        plugin = {"pretty", "html:testOutput/cucumber-reports/index.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:testOutput/ExecutionTimeline"},
        monochrome = true,
        features = {"src/test/resources/features"},
        glue = {"stepdefinitions"}
        // tags = "@hs"
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
