Feature: Add to cart

  Scenario Outline: Add one quantity from Store
#    TODO close cookies button does not work
    Given I'm on intercars main page
    When I click select vehicle
    And I select vehicle: <brand>, <model> <model_year>, <engine>, <engine_catalog_number>
    And I select "tires"
    And I add 4 items with index 1 to chart
    Then I go to chart
    And I see selected product
    And I delete product
    And I can see empty chart
    Examples:
      | brand  | model      | model_year    | engine    | engine_catalog_number |
      | "Audi" | "A3 (8P1)" | "2003 - 2012" | "1.6 FSI" | "(BLP, BLF, BAG)"     |
      | "BMW"  | "3 (E46)"  | "1997 - 2005" | "318 d"   | "(M47 D20 (204D1))"   |