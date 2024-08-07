package pl.t_mobile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/pl/t_mobile/features",
        glue = "pl/t_mobile/steps")
public class RunCucumberTest extends AbstractTestNGCucumberTests {

}