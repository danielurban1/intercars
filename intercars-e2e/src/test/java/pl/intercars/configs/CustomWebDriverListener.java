package pl.intercars.configs;



import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.Arrays;

@Slf4j
public class CustomWebDriverListener implements WebDriverListener {

    public void beforeGetAttribute(WebElement element, String name) {
        log.info("Trying to get attribute: {} from: {}", name, element);
    }

    public void afterGetAttribute(WebElement element, String name, String result) {
        log.info("Attribute: {} from {} -> {}", name, element, result);
    }

    public void beforeGet(WebDriver driver, String url) {
        log.info("Trying to open: {}", url);
    }

    public void afterGet(WebDriver driver, String url) {
        log.info("Opened: {}", url);
    }

    public void beforeGetCurrentUrl(WebDriver driver) {
        log.info("Trying to get current url");
    }

    public void afterGetCurrentUrl(String result, WebDriver driver) {
        log.info("Current url: " + result);
    }

    public void beforeGetTitle(WebDriver driver) {
        log.info("Trying to get current page title");
    }

    public void afterGetTitle(WebDriver driver, String result) {
        log.info("Current page title: {}", result);
    }

//    public void beforeFindElement(WebDriver driver, By locator) {
//        log.info("Trying to find element by: {}", locator.toString());
//    }
//
//    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
//        log.info("Found element: {} -> {}", locator.toString(), result.toString());
//    }

    public void beforeQuit(WebDriver driver) {
        log.info("Trying to close driver: {}", driver );
    }

    public void afterQuit(WebDriver driver) {
        log.info("Successfully closed driver: {}", driver );
    }

    public void beforeExecuteScript(WebDriver driver, String script, Object[] args) {
        log.info("Executing script: {}", script);
    }

    public void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
        log.info("Successfully executed script: {}", script);
    }

    public void beforeClick(WebElement element) {
        log.info("Trying to click: {}", element.toString());
    }

    public void afterClick(WebElement element) {
        log.info("Successfully clicked: {}", element.toString());
    }

    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("Trying to input value: {} into: {}", Arrays.toString(keysToSend), element.toString());
    }

    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("Successfully imputed value: {} into: {}", Arrays.toString(keysToSend), element.toString());
    }

    public void beforeClear(WebElement element) {
        log.info("Trying to clear: {}", element.toString());
    }

    public void afterClear(WebElement element) {
        log.info("Successfully cleared: {}", element.toString());
    }

    public void beforeGetText(WebElement element) {
        log.info("Trying to get text from: {}", element.toString());
    }

    public void afterGetText(WebElement element, String result) {
        log.info("Text from: {} -> {}", element.toString(), result);
    }
}
