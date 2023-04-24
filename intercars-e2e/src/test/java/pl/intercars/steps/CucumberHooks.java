package pl.intercars.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import pl.intercars.configs.Context;
import pl.intercars.enums.SupportedBrowser;

import static pl.intercars.configs.WebDriverFactory.initializeDrive;
@Getter
@AllArgsConstructor
public class CucumberHooks {
    private Context context;
    @Before
    public void before(){
        String browser = System.getProperty("browser");
        if (browser == null) browser = SupportedBrowser.CHROME.name();
        context.driver = initializeDrive(browser);
        context.driver.manage().window().maximize();
    }

    @After
    public void after(){
        if(context.getDriver() != null){
            context.driver.quit();
        }
    }
}
