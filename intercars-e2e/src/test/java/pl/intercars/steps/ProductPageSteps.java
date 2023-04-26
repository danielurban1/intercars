package pl.intercars.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pl.intercars.configs.Context;
import pl.intercars.enums.Category;
import pl.intercars.models.Product;
import pl.intercars.pages.ChartPage;
import pl.intercars.pages.MainPage;
import pl.intercars.pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageSteps {
    private final Context context;
    private final ProductsPage productsPage;

    public ProductPageSteps(Context context) {
        this.context = context;
        this.productsPage = new ProductsPage(this.context.getDriver());
    }
    @And("I select {string}")
    public void iSelect(final String category) throws InterruptedException {
        Category categoryEnum = Category.valueOf(category.toUpperCase());
        productsPage.selectCategory(categoryEnum);
        assertThat(productsPage.getTitleLabel()).contains(categoryEnum.getName());
    }

    @And("I add {int} items with index {int} to chart")
    public void iAddItemsWithIndexToChart(final int quantity, final int index) {
        Product product = new Product(productsPage.getProductNameText(index), productsPage.getProductPriceText(index), quantity);
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
                .contains(context.getProduct().getUnitPrice());
        productsPage.clickNextButton();
    }
}
