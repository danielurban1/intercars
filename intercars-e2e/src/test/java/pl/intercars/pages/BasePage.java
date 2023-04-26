package pl.intercars.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

import static pl.intercars.configs.GlobalArguments.*;

public abstract class BasePage {
    protected WebDriver driver;
    protected final Wait<WebDriver> fluentWait;
    protected Select select;
    protected JavascriptExecutor js;

    protected BasePage(final WebDriver driver){
        this.driver = driver;
        this.fluentWait = new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(DRIVER_FLUENT_WAIT_POOLING_IN_MILLIS))
                .withTimeout(Duration.ofSeconds(DRIVER_FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DRIVER_IMPLICIT_WAIT_IN_SECONDS));
        js = (JavascriptExecutor) driver;
    }

    protected void waitForElementToBeClickable(WebElement element){
        fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBeClickable(By by){
        fluentWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void waitForElementToBeVisible(WebElement element){
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeVisible(By by){
        fluentWait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }
    protected void waitForElementToDisappear(WebElement element){
        fluentWait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected boolean isElementVisible(WebElement element){
        boolean isVisible;
        try{
            waitForElementToBeVisible(element);
            isVisible = true;
        }
        catch (NoSuchElementException | TimeoutException e){
            isVisible = false;
        }
        return isVisible;
    }

    public void scrollIntoViewJS(WebElement element) throws InterruptedException {
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(500);
    }
}
