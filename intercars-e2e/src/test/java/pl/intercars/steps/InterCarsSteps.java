package pl.intercars.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pl.intercars.configs.Context;
import pl.intercars.configs.PageFactoryManager;
import pl.intercars.enums.Category;
import pl.intercars.models.Product;
import pl.intercars.pages.ChartPage;
import pl.intercars.pages.MainPage;
import pl.intercars.pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;


public class InterCarsSteps {
    private Context context;
    private MainPage mainPage;
    private ProductsPage productsPage;
    private ChartPage chartPage;

    public InterCarsSteps(Context context) {
        this.context = context;
//        mainPage = PageFactoryManager.getMainPage(context.getDriver());
        mainPage = new MainPage(this.context.getDriver());
        productsPage = new ProductsPage(this.context.getDriver());
        productsPage = new ProductsPage(this.context.getDriver());
        chartPage = new ChartPage(this.context.getDriver());
    }

    @Given("I'm on intercars main page")
    public void iMOnIntercarsMainPage() {
        mainPage.openPage();
        assertThat(context.getDriver().getTitle())
                .isEqualTo("Sklep motoryzacyjny Inter Cars - Części samochodowe online - Opony, akumulatory, części samochodowe");
    }

    @When("I click select vehicle")
    public void iClickSelectVehicle() {
        mainPage.clickCarIcon();
        assertThat(mainPage.isVehicleSelectorPopupVisible()).isTrue();
    }

    @And("I select vehicle: {string}, {string} {string}, {string}, {string}")
    public void iSelectVehicleBrandModelModelYearEngine(final String brand, final String model, final String modelYear,
                                                        final String engine, final String engineCatalogNumber) throws InterruptedException {
        mainPage.selectVehicleBrand(brand);
        assertThat(mainPage.getVehicleBrandLabelText()).isEqualTo(brand);
        mainPage.selectVehicleModel(model, modelYear);
        assertThat(mainPage.getSelectedVehicleModelLabelText()).isEqualTo(model);
        assertThat(mainPage.getSelectedVehicleModelYearLabelText()).isEqualTo(modelYear);
        mainPage.selectEngineType(engine, engineCatalogNumber);
        assertThat(mainPage.getSelectedEngineTypeLabelText()).contains(engine);
        mainPage.clickSelectButton();
    }

    @And("I select {string}")
    public void iSelect(final String category) throws InterruptedException {
        Category categoryEnum = Category.valueOf(category.toUpperCase());
        productsPage.selectCategory(categoryEnum);
        assertThat(productsPage.getTitleLabel()).contains(categoryEnum.getName());
    }

    @And("I add {int} items with index {int} to chart")
    public void iAddItemsWithIndexToChart(final int quantity, final int index) throws InterruptedException {
        Product product = new Product(productsPage.getProductName(index), productsPage.getProductPrice(index), quantity);
        productsPage.inputProductQuantity(index, quantity);
        productsPage.clickSubmitButton(index);
        context.setProduct(product);
    }

    @Then("I go to chart")
    public void iGoToChart() {
        assertThat(productsPage.getProductInChartNameLabelText()).contains(context.getProduct().getName());
        assertThat(productsPage.getProductInChartQuantityLabelText())
                .contains(context.getProduct().getQuantity() + " ");
        assertThat(productsPage.getProductInChartPriceLabelText())
                .contains(String.valueOf(context.getProduct().getPriceValue()).replace(".", ",")
                        + " " + context.getProduct().getCurrency());
        productsPage.clickNextButton();
    }

    @And("I see selected product")
    public void iSeeSelectedProduct() {
        assertThat(chartPage.getProductNameLabelText()).isEqualTo(context.getProduct().getName());
        assertThat(chartPage.getProductPriceLabelText())
                .isEqualTo(String.valueOf(context.getProduct().getPriceValue()).replace(".", ",")
                        + " " + context.getProduct().getCurrency());
        assertThat(chartPage.getProductQuantityLabelValue())
                .isEqualTo(String.valueOf(context.getProduct().getQuantity()));
        String expectedValue = String.valueOf(context.getProduct().getQuantity()
                        * context.getProduct().getPriceValue()).replace(".", ",")
                .replace(".", ",")
                + " " + context.getProduct().getCurrency();
        assertThat(chartPage.getProductValueLabelText()).isEqualToIgnoringWhitespace(expectedValue);
    }

    @And("I delete product")
    public void iDeleteProduct() {
        chartPage.clickClearButton();
    }

    @And("I can see empty chart")
    public void iCanSeeEmptyChart() {
        assertThat(chartPage.getTitleLabel()).isEqualTo("Twój koszyk jest pusty");
    }
}
