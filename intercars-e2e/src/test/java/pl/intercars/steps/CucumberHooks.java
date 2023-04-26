package pl.intercars.steps;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pl.intercars.configs.Context;
import pl.intercars.enums.SupportedBrowser;

import java.io.ByteArrayInputStream;
import java.net.UnknownHostException;

import static pl.intercars.configs.BrowserMobConfig.*;
import static pl.intercars.configs.DevToolsListener.logDevToolsConsoleEvents;
import static pl.intercars.configs.WebDriverFactory.initializeDrive;
import static pl.intercars.configs.WebDriverUtils.takeScreenshot;
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

    @After
    public void after(Scenario scenario){
        if (scenario.isFailed()){
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(takeScreenshot(context.getDriver())));
        }
        stopMobProxyServer();
        if(context.getDriver() != null){
            context.getDriver().quit();
        }
        log.info("\n\n\n");
    }


}
