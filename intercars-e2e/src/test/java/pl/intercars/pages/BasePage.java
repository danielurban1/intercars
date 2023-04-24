package pl.intercars.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected final Wait<WebDriver> fluentWait;
    protected Select select;
    protected JavascriptExecutor js;

    protected BasePage(final WebDriver driver){
        this.driver = driver;
        this.fluentWait = new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(500))
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
    }

    protected void waitForElementToBeClickable(WebElement element){
        fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBeVisible(WebElement element){
        fluentWait.until(ExpectedConditions.visibilityOf(element));
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
        Thread.sleep(1000);
    }
}
