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
import static pl.intercars.enums.SupportedBrowser.getSupportedBrowser;

public class WebDriverFactory {
    public static WebDriver initializeDrive(final String browser) throws UnknownHostException {
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

    private static WebDriver initializeChrome() throws UnknownHostException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.setProxy(getSeleniumProxy());
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver initializeFireFox() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        firefoxOptions.addPreference("network.automatic-ntlm-auth.trusted-uris", "http://,https://");
        firefoxOptions.addPreference("network.automatic-ntlm-auth.allow-non-fqdn", true);
        firefoxOptions.addPreference("network.negotiate-auth.delegation-uris", "http://,https://");
        firefoxOptions.addPreference("network.negotiate-auth.trusted-uris", "http://,https://");
        firefoxOptions.addPreference("network.http.phishy-userpass-length", 255);
        firefoxOptions.addPreference("security.csp.enable", false);
        firefoxOptions.addPreference("network.proxy.no_proxies_on", "");
        return new FirefoxDriver();
    }

    private static WebDriver setupDriverListener(WebDriver driver){
        WebDriverListener customWebDriverListener = new CustomWebDriverListener();
        return new EventFiringDecorator<>(customWebDriverListener).decorate(driver);
    }
}
