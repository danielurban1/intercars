package pl.intercars.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pl.intercars.configs.Context;
import pl.intercars.enums.Category;
import pl.intercars.models.Product;
import pl.intercars.pages.ChartPage;
import pl.intercars.pages.MainPage;
import pl.intercars.pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;


public class MainPageSteps {
    private final Context context;
    private final  MainPage mainPage;


    public MainPageSteps(Context context) {
        this.context = context;
        this.mainPage = new MainPage(this.context.getDriver());
    }

    @Given("I'm on intercars main page")
    public void iMOnIntercarsMainPage() {
        mainPage.openPage();
        String pageTitle = "Sklep motoryzacyjny Inter Cars - Części samochodowe online - Opony, akumulatory, części samochodowe";
        assertThat(context.getDriver().getTitle())
                .isEqualTo(pageTitle);
    }

    @And("I accepted cookie policy")
    public void iAcceptedCookiePolicy() {
        mainPage.clickAcceptCookiesButton();
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
}
