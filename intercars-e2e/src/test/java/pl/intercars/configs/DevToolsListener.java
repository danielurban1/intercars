package pl.intercars.configs;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v110.console.Console;
import org.openqa.selenium.devtools.v85.log.Log;
import pl.intercars.enums.SupportedBrowser;

import static org.openqa.selenium.devtools.v85.log.model.LogEntry.Level.ERROR;
import static org.openqa.selenium.devtools.v85.log.model.LogEntry.Level.WARNING;
import static pl.intercars.enums.SupportedBrowser.CHROME;

@Slf4j
public class DevToolsListener {
    public static void logDevToolsConsoleEvents(final WebDriver driver, final SupportedBrowser supportedBrowser){
        if(supportedBrowser.equals(CHROME)){
            chromeLogger(driver);
        }
        else {
            log.warn("DevTools console logging is supported only on chrome, console logs will not be displayed!");
        }
    }
    private static void chromeLogger(final WebDriver driver){
        log.info("Setting up DevTools console logging");
        DevTools devTools = ((HasDevTools)driver).getDevTools();
        devTools.createSession();
        devTools.send(Log.enable());
        devTools.addListener(Log.entryAdded(), entry -> {
            if(entry.getLevel().equals(WARNING) || entry.getLevel().equals(ERROR)){
                log.error("level: {} \n" +
                                "source: {}\n" +
                                "text: {}",
                        entry.getLevel(),
                        entry.getSource(),
                        entry.getText()
                );
            }
        });
    }
}
