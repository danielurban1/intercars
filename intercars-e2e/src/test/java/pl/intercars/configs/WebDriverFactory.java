package pl.intercars.configs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import pl.intercars.enums.SupportedBrowser;

import java.net.UnknownHostException;

import static pl.intercars.configs.BrowserMobConfig.getSeleniumProxy;
import static pl.intercars.configs.GlobalArguments.DRIVER_ARGUMENTS;
import static pl.intercars.enums.SupportedBrowser.getSupportedBrowser;

public class WebDriverFactory {
    public static WebDriver initializeDrive(final String browser) {
        SupportedBrowser supportedBrowser;
        if (browser == null) {
            supportedBrowser = SupportedBrowser.CHROME;
        }
        else{
            supportedBrowser = getSupportedBrowser(browser);
        }
        WebDriver driver = null;
        switch (supportedBrowser) {
            case CHROME:
                driver = initializeChrome();
                break;
            case FIREFOX:
                driver = initializeFireFox();
                break;
        }
        return setupDriverListener(driver);
    }

    private static WebDriver initializeChrome() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(DRIVER_ARGUMENTS);
        chromeOptions.setProxy(getSeleniumProxy());
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver initializeFireFox() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments(DRIVER_ARGUMENTS);
        firefoxOptions.setProxy(getSeleniumProxy());
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver setupDriverListener(WebDriver driver){
        WebDriverListener customWebDriverListener = new CustomWebDriverListener();
        return new EventFiringDecorator<>(customWebDriverListener).decorate(driver);
    }
}
