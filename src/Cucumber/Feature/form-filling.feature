Feature: Filling a 'New address' form
  Scenario Outline: User can fill a form with params
    Given I'm on shop main page
    When I'm loggin to my account
    And I click 'Addresses' link
    And I click to 'Create new address'
    And I enter alias "<alias>", address "<address>", city "<city>", postal code "<postalCode>", country "<country>" and phone "<phone>"
    Then I can see success message "Address successfully added!"
    And I click 'Delete' button
    Then I see success message "Address successfully deleted!"
    And I close the browser

    Examples:
    | alias    | address       | city        | postalCode  | country         | phone     |
    | Karolp   | Nowa 1        | Nowe Miasto | 11-111      | United Kingdom  | 123456789 |