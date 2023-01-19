Feature: A set of scenarios for testing the "example" module

  Scenario: User successfully shares an item
    Given A user enters the landing page
    And A user enters a product page
    When A user presses share button
    Then A new tab is opened for the user


  Scenario: Admin successfully deletes an item
    When An admin is in logged in
    And An Admin entered the products page
    And An admin selects the deletion of the item
    When An admin confirms deleting the item
    Then An admin receives an alert "Product successfully deleted."


  Scenario: Testing how a case where a user adds a product to the cart
    Given A user enters the landing page
    And A user enters a product page
    And An admin is in logged in
    And An Admin entered the products page
    And An admin selects the deletion of the item
    And An admin confirms deleting the item
    When A user presses share button
    Then We return to an error