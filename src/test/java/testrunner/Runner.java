package testrunner;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)


@CucumberOptions(features="./testcases/TS20.feature",

//				dryRun = true,
				glue={"com.upp.stepdefinition","com.upp.hooks"},
				monochrome=true,
				strict = true,
				plugin = { "pretty"
						,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
}
				)

public class Runner {
	
}
 