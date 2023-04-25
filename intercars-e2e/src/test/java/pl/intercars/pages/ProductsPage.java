package pl.intercars.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.intercars.enums.Category;

public class ProductsPage extends BasePage{
    @FindBy(xpath = "//h1")
    private WebElement titleLabel;

    @FindBy(xpath = "//div[contains(@class, 'simple-cart-name') and contains(@class, 'span10')]")
    private WebElement productInChartNameLabel;

    @FindBy(xpath = "//div[contains(@class, 'simple-cart-name') and contains(@class, 'prices')]")
    private WebElement productInChartQuantityLabel;

    @FindBy(xpath = "//span[contains(@class, 'simple-cart-name') and contains(@class, 'current-price')]")
    private WebElement productInChartPriceLabel;

    @FindBy(xpath="//div[@class='align-right']//button[@id='checkout']")
    private WebElement nextButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void selectCategory(final Category category) throws InterruptedException {
        WebElement element = driver.findElement(
                By.xpath("//section[@id='sidebar']//a[@href='" + category.getHref() + "']"));
        scrollIntoViewJS(element);
        element.click();
    }

    public String getTitleLabel(){
        waitForElementToBeVisible(titleLabel);
        return titleLabel.getText();
    }

    public String getProductName(final int index){
        WebElement element = driver.findElement(
                By.xpath("//div[@id='promoted-products']//div[@class='clearfix']["
                        + index + "]//a[@class='prod-label']"));
        return element.getText();
    }

    public String getProductPrice(final int index){
        WebElement element = driver.findElement(
                By.xpath("//div[@id='promoted-products']//div[@class='clearfix']["
                        + index + "]//span[@class='current-price nowrap']"));
        return element.getText();
    }

    public void inputProductQuantity(final int index, final int quantity){
        WebElement element = driver.findElement(
                By.xpath("//div[@id='promoted-products']//div[@class='clearfix']["
                        + index + "]//input[@name='quantity']"));
        element.clear();
        element.sendKeys(String.valueOf(quantity));
    }

    public void clickSubmitButton(final int index) throws InterruptedException {
        WebElement element = driver.findElement(
                By.xpath("//div[@id='promoted-products']//div[@class='clearfix']["
                        + index + "]//button[@type='submit']"));
        element.click();
    }

    public String getProductInChartNameLabelText(){
        waitForElementToBeVisible(productInChartNameLabel);
        return productInChartNameLabel.getText();
    }

    public String getProductInChartQuantityLabelText(){
        waitForElementToBeVisible(productInChartQuantityLabel);
        return productInChartQuantityLabel.getText();
    }

    public String getProductInChartPriceLabelText(){
        waitForElementToBeVisible(productInChartPriceLabel);
        return productInChartPriceLabel.getText();
    }

    public void clickNextButton(){
        waitForElementToBeClickable(nextButton);
        nextButton.click();
    }
}
