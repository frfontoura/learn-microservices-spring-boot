package microservices.book;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author frfontoura
 * @version 1.0 04/04/2020
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty",
        "html:target/cucumber",
        "junit:target/junit-report.xml" },
        features = "src/test/resources/multiplication.feature")
public class MultiplicationFeatureTest {
}
