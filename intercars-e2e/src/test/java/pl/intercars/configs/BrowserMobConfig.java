package pl.intercars.configs;

import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class BrowserMobConfig {
    private static BrowserMobProxy proxy;

    public static void startMobProxyServer() throws UnknownHostException {
        log.info("Starting Browser Mob Proxy");
        proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.start(1234);
        log.info("Successfully started Browser Mob Proxy: {}", proxy.getChainedProxy());
    }

    public static Proxy getSeleniumProxy() {
        log.info("Creating selenium proxy");
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        String httpAddress = "localhost:" + proxy.getPort();
        seleniumProxy.setHttpProxy(httpAddress);
        seleniumProxy.setSslProxy(httpAddress);
        log.info("Created selenium proxy for http and ssl: {}", httpAddress);
        return seleniumProxy;
    }

    public static void logHttpTraffic(final boolean logAll){
        String logMessage = logAll? "Logging all http traffic": "Logging errors only";
        log.info(logMessage);
        proxy.addResponseFilter(((httpResponse, httpMessageContents, httpMessageInfo) -> {
            if (logAll && httpResponse.status().code() < 400){
                log.info("uri: {}{}\nresponse status: {}\nresponse content: {}",
                        httpMessageInfo.getOriginalUrl(),
                        httpMessageInfo.getOriginalRequest().uri(),
                        httpResponse.status().code(),
                        httpMessageContents.getTextContents());
            }
            else if(httpResponse.status().code() >= 400){
                log.error("Request URL: {}{}" +
                                "\nheaders: {}" +
                                "\nmethod: {}" +
                                "\nresponse status: {}" +
                                "\nresponse content: {}",
                        httpMessageInfo.getOriginalUrl(),
                        httpMessageInfo.getOriginalRequest().uri(),
                        httpMessageInfo.getOriginalRequest().headers().toString(),
                        httpMessageInfo.getOriginalRequest().method(),
                        httpResponse.status().code(),
                        httpMessageContents.getTextContents());
            }
        }
                ));
    }

    public static void stopMobProxyServer(){
        log.info("Stopping new Browser Mob Proxy");
        proxy.stop();
        log.info(" Successfully stopped new Browser Mob Proxy");
    }
}
