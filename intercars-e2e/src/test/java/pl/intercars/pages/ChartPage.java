package pl.intercars.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChartPage extends BasePage{
    @FindBy(className = "page-header")
    private WebElement titleLabel;

    @FindBy(xpath = "//tr[@data-item-position='1']//li/a")
    private WebElement productNameLabel;

    @FindBy(xpath = "//tr[@data-item-position='1']//td[@class='v-align-middle']")
    private WebElement productPriceLabel;

    @FindBy(xpath = "//tr[@data-item-position='1']//input[@name='quantity']")
    private WebElement productQuantityLabel;

    @FindBy(xpath = "//tr[@data-item-position='1']//strong[@class='snapshot-price nowrap']")
    private WebElement productValueLabel;

    @FindBy(xpath = "//tr[@data-item-position='1']//button[@class='clean-button']")
    private WebElement clearButton;


    public ChartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getTitleLabel(){
        waitForElementToBeVisible(titleLabel);
        return titleLabel.getText();
    }
    public String getProductNameLabelText(){
        waitForElementToBeVisible(productNameLabel);
        return productNameLabel.getText();
    }

    public String getProductPriceLabelText(){
        waitForElementToBeVisible(productPriceLabel);
        return productPriceLabel.getText();
    }

    public String getProductQuantityLabelValue(){
        waitForElementToBeVisible(productQuantityLabel);
        return productQuantityLabel.getAttribute("value");
    }

    public String getProductValueLabelText(){
        waitForElementToBeVisible(productValueLabel);
        return productValueLabel.getText();
    }

    public void clickClearButton(){
        waitForElementToBeClickable(clearButton);
        clearButton.click();
    }
}
