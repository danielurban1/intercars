package pl.intercars.configs;

import org.openqa.selenium.WebDriver;
import pl.intercars.pages.ChartPage;
import pl.intercars.pages.MainPage;
import pl.intercars.pages.ProductsPage;

public class PageFactoryManager {
    private static ChartPage chartPage;
    private static MainPage mainPage;
    private static ProductsPage productsPage;

    public static MainPage getMainPage(WebDriver driver) {
        return mainPage == null ? new MainPage(driver) : mainPage;
    }
}
