package pl.intercars.steps;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import pl.intercars.configs.Context;
import pl.intercars.enums.SupportedBrowser;

import static pl.intercars.configs.WebDriverFactory.initializeDrive;
@Slf4j
@Getter
@AllArgsConstructor
public class CucumberHooks {
    private Context context;
    @Before
    public void before(Scenario scenario){
        log.info("Running scenario: " + scenario.getName());
        String browser = System.getProperty("browser");
        log.info("Selected browser: " + browser);
        context.driver = initializeDrive(browser);
        context.driver.manage().window().maximize();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario){
        //TODO implement custom method log current step name
    }

    @AfterStep
    public void afterStep(Scenario scenario){
    }

    @After
    public void after(){
        if(context.getDriver() != null){
            context.driver.quit();
        }
        log.info("\n\n\n");
    }


}
