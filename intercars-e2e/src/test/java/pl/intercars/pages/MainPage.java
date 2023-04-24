package pl.intercars.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage{
    public static final String URL = "https://intercars.pl/";

    @FindBy(className = "icon-car")
    private WebElement carIcon;

    @FindBy(id = "jqs-vehicle-chain-selector")
    private  WebElement vehicleSelectorPopup;
    @FindBy(xpath = "//a[@id='jqs-vehicle-brands-button']/span[1]")
    private WebElement vehicleBrandLabel;

    @FindBy(xpath = "//a[@tabindex = '3']/span[1]")
    private WebElement vehicleModelLabel;

    @FindBy(xpath = "//a[@tabindex = '3']//td[1]")
    private WebElement selectedVehicleModelLabel;

    @FindBy(xpath = "//a[@tabindex = '3']//td[2]")
    private WebElement selectedVehicleModelYearLabel;

    @FindBy(xpath = "//a[@tabindex = '4']/span[1]")
    private WebElement engineTypeLabel;

    @FindBy(xpath = "//a[@tabindex = '4']//tr[@class='selected-show']")
    private WebElement selectedEngineTypeLabel;

    @FindBy(xpath = "//button[contains(@class, 'jqs-save-vehicle')]")
    private WebElement selectButton;
    public MainPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage(){
        driver.get(URL);
    }
    public void clickCarIcon() {
        waitForElementToBeClickable(carIcon);
        carIcon.click();
    }

    public boolean isVehicleSelectorPopupVisible(){
        return isElementVisible(vehicleSelectorPopup);
    }

    public void selectVehicleBrand(final String carBrand) throws InterruptedException {
        waitForElementToBeClickable(vehicleBrandLabel);
        vehicleBrandLabel.click();
        WebElement element = driver.findElement(
                By.xpath("//ul[@id='jqs-vehicle-brands-menu']//a[text()='" + carBrand + "']"));
        scrollIntoViewJS(element);
        element.click();
    }

    public String getVehicleBrandLabelText(){
        waitForElementToBeVisible(vehicleBrandLabel);
        return vehicleBrandLabel.getText();
    }

    public void selectVehicleModel(final String model, final String modelYear) throws InterruptedException {
       Thread.sleep(300);
        waitForElementToBeClickable(vehicleModelLabel);
        vehicleModelLabel.click();
        WebElement element = driver.findElement(
                By.xpath("//tr/td[text()='" + model + "']/following-sibling::td[text()='" + modelYear + "']"));
        scrollIntoViewJS(element);
        element.click();
    }

    public String getSelectedVehicleModelLabelText(){
        waitForElementToBeVisible(selectedVehicleModelLabel);
        return selectedVehicleModelLabel.getText();
    }

    public String getSelectedVehicleModelYearLabelText(){
        waitForElementToBeVisible(selectedVehicleModelYearLabel);
        return selectedVehicleModelYearLabel.getText();
    }

    public void selectEngineType(final String engine, final String engineCatalogNumber ) throws InterruptedException {
        Thread.sleep(300);
        waitForElementToBeClickable(engineTypeLabel);
        engineTypeLabel.click();
        WebElement element = driver.findElement(
                By.xpath("//td[@valign='top' and text() = '" + engine + " " + engineCatalogNumber + "']"));
        scrollIntoViewJS(element);
        element.click();
    }

    public String getSelectedEngineTypeLabelText(){
        waitForElementToBeVisible(selectedEngineTypeLabel);
        return selectedEngineTypeLabel.getText();
    }

    public void clickSelectButton(){
        waitForElementToBeClickable(selectButton);
        selectButton.click();
    }
}
