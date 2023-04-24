package pl.intercars.configs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pl.intercars.enums.SupportedBrowser;

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
        return driver;
    }

    private static WebDriver initializeChrome() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
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
}
