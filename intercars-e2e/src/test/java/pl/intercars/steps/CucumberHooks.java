package pl.intercars.steps;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import pl.intercars.configs.Context;
import pl.intercars.enums.SupportedBrowser;

import java.net.UnknownHostException;

import static pl.intercars.configs.BrowserMobConfig.*;
import static pl.intercars.configs.DevToolsListener.logDevToolsConsoleEvents;
import static pl.intercars.configs.WebDriverFactory.initializeDrive;
import static pl.intercars.enums.SupportedBrowser.getSupportedBrowser;

@Slf4j
@Getter
@AllArgsConstructor
public class CucumberHooks {
    private Context context;
    @Before
    public void before(Scenario scenario) throws UnknownHostException {
        log.info("Running scenario: " + scenario.getName());
        String browser = System.getProperty("browser");
        log.info("Selected browser: " + browser);
        startMobProxyServer();
        logHttpTraffic(false);
        context.setDriver(initializeDrive(browser));
        logDevToolsConsoleEvents(context.getDriver(), getSupportedBrowser(browser));
        context.getDriver().manage().window().maximize();
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
        stopMobProxyServer();
        if(context.getDriver() != null){
            context.getDriver().quit();
        }
        log.info("\n\n\n");
    }


}
