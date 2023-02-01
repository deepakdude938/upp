package testrunner;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="./testcases/newLead.feature",
//				dryRun = true,
				glue={"com.upp.stepdefinition","com.upp.hooks"},
				monochrome=true,
				strict = true,
				plugin = { "pretty", "html:report/htmlReport" 
						,"json:report/jsonReport/jsonReport.json" 
						,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"

}
				)

public class Runner {
	

	 
}
 