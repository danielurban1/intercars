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

    private final String promptedProductsXpath = "//div[@id='promoted-products']//div[@class='clearfix']";
    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private By getCategoryLabelBy(final Category category){
        return By.xpath("//section[@id='sidebar']//a[@href='" + category.getHref() + "']");
    }
    public void selectCategory(final Category category) throws InterruptedException {
        By by = getCategoryLabelBy(category);
        waitForElementToBeClickable(by);
        WebElement element = driver.findElement(by);
        scrollIntoViewJS(element);
        element.click();
    }

    public String getTitleLabel(){
        waitForElementToBeVisible(titleLabel);
        return titleLabel.getText();
    }

    private By getProductBy(final int index){
        return By.xpath(promptedProductsXpath +
                "[" + index + "]//a[@class='prod-label']");
    }

    public String getProductNameText(final int index){
        By by = getProductBy(index);
        waitForElementToBeVisible(by);
        return driver.findElement(by).getText();
    }

    private By getProductPriceLabelBy(final int index){
        return By.xpath(promptedProductsXpath +
                "[" + index + "]//span[@class='current-price nowrap']");
    }

    public String getProductPriceText(final int index){
        By by = getProductPriceLabelBy(index);
        waitForElementToBeVisible(by);
        return driver.findElement(by).getText();
    }

    private By getProductQuantityLabelBy(final int index){
        return By.xpath(promptedProductsXpath +
                "[" + index + "]//input[@name='quantity']");
    }

    public void inputProductQuantity(final int index, final int quantity){
        By by = getProductQuantityLabelBy(index);
        waitForElementToBeClickable(by);
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(String.valueOf(quantity));
    }

    private By getSubmitButtonBy(final int index){
        return By.xpath(promptedProductsXpath +
                "[" + index + "]//button[@type='submit']");
    }
    public void clickSubmitButton(final int index) {
        By by = getSubmitButtonBy(index);
        waitForElementToBeClickable(by);
        driver.findElement(by).click();
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
