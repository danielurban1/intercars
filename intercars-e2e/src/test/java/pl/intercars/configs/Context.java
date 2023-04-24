package pl.intercars.configs;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import pl.intercars.models.Product;

@Getter
@Setter
public class Context {
    public WebDriver driver;

    public Product product;
}
