package pl.intercars.configs;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import pl.intercars.models.Product;

@Getter
@Setter
public class Context {
    private WebDriver driver;

    private Product product;
}
