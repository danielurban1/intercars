import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "pl.intercars",
        features = "src/test/resources/features",
        publish = true)
public class TestRunner {
}
