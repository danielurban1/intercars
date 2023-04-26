import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static pl.intercars.configs.GlobalArguments.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = CUCUMBER_GLUE,
        features = FEATURES,
        plugin = {"pretty", ALLURE_PLUGIN})
public class TestRunner {
}
