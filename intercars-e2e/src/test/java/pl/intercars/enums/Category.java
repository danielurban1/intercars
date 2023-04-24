package pl.intercars.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    TIRES("Opony", "/szukaj/opony-201/?search_type=products");
    private final String name;
    private final String href;
}
