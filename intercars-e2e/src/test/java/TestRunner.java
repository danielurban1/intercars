import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static pl.intercars.configs.GlobalArguments.CUCUMBER_GLUE;
import static pl.intercars.configs.GlobalArguments.FEATURES;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = CUCUMBER_GLUE,
        features = FEATURES,
        publish = true)
public class TestRunner {
}
