package pl.intercars.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static pl.intercars.configs.GlobalArguments.URL;

public class MainPage extends BasePage{
    public final String url = URL;

    @FindBy(xpath = "//button[@onclick='approveCookie()']")
    private WebElement acceptCookiesButton;

    @FindBy(className = "icon-car")
    private WebElement carIcon;

    @FindBy(id = "jqs-vehicle-chain-selector")
    private  WebElement vehicleSelectorPopup;

    @FindBy(className = "modal-header")
    private  WebElement selectVehicleByBrandAndModelLabel;

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
        driver.get(url);
    }

    public void clickAcceptCookiesButton(){
        waitForElementToBeClickable(acceptCookiesButton);
        acceptCookiesButton.click();
        waitForElementToDisappear(acceptCookiesButton);
    }

    public void clickCarIcon() {
        waitForElementToBeClickable(carIcon);
        carIcon.click();
    }

    public boolean isVehicleSelectorPopupVisible(){
        return isElementVisible(vehicleSelectorPopup);
    }

    public void clickSelectVehicleByBrandAndModeLabel(){
        waitForElementToBeClickable(selectVehicleByBrandAndModelLabel);
        selectVehicleByBrandAndModelLabel.click();
    }

    private By vehicleBrandOptionBy(final String carBrand){
        return By.xpath("//ul[@id='jqs-vehicle-brands-menu']//a[text()='" + carBrand + "']");
    }

    public void selectVehicleBrand(final String carBrand) throws InterruptedException {
        waitForElementToBeClickable(vehicleBrandLabel);
        vehicleBrandLabel.click();
        WebElement element = driver.findElement(vehicleBrandOptionBy(carBrand));
        scrollIntoViewJS(element);
        waitForElementToBeClickable(element);
        element.click();
    }

    public String getVehicleBrandLabelText(){
        waitForElementToBeVisible(vehicleBrandLabel);
        return vehicleBrandLabel.getText();
    }

    private By vehicleModelOption(final String model, final String modelYear){
        return By.xpath("//tr/td[text()='" + model + "']/following-sibling::td[text()='" + modelYear + "']");
    }
    public void selectVehicleModel(final String model, final String modelYear) throws InterruptedException {
        clickSelectVehicleByBrandAndModeLabel();
        waitForElementToBeClickable(vehicleModelLabel);
        vehicleModelLabel.click();
        WebElement element = driver.findElement(vehicleModelOption(model, modelYear));
        scrollIntoViewJS(element);
        waitForElementToBeClickable(element);
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

    private By engineTypeOption(final String engine, final String engineCatalogNumber){
        return By.xpath("//td[@valign='top' and text() = '" + engine + " " + engineCatalogNumber + "']");
    }
    public void selectEngineType(final String engine, final String engineCatalogNumber) throws InterruptedException {
        clickSelectVehicleByBrandAndModeLabel();
        waitForElementToBeClickable(engineTypeLabel);
        engineTypeLabel.click();
        WebElement element = driver.findElement(engineTypeOption(engine, engineCatalogNumber));
        scrollIntoViewJS(element);
        waitForElementToBeClickable(element);
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
