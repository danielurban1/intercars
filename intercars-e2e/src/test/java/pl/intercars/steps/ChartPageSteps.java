package pl.intercars.steps;

import io.cucumber.java.en.And;
import pl.intercars.configs.Context;
import pl.intercars.pages.ChartPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ChartPageSteps {
    private final Context context;
    private final ChartPage chartPage;

    public ChartPageSteps(Context context) {
        this.context = context;
        this.chartPage = new ChartPage(this.context.getDriver());
    }


    @And("I see selected product")
    public void iSeeSelectedProduct() {
        assertThat(chartPage.getProductNameLabelText())
                .isEqualTo(context.getProduct().getName());
        assertThat(chartPage.getProductPriceLabelText())
                .isEqualTo(context.getProduct().getUnitPrice());
        assertThat(chartPage.getProductQuantityLabelValue())
                .isEqualTo(String.valueOf(context.getProduct().getQuantity()));
        assertThat(chartPage.getProductValueLabelText())
                .isEqualToIgnoringWhitespace(context.getProduct().getFullPrice());
    }

    @And("I delete product")
    public void iDeleteProduct() {
        chartPage.clickClearButton();
    }

    @And("I can see empty chart")
    public void iCanSeeEmptyChart() {
        String emptyChartWording = "Twój koszyk jest pusty";
        assertThat(chartPage.getTitleLabel()).isEqualTo(emptyChartWording);
    }
}
