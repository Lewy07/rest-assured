Feature: Task Feature

  Scenario: Exchange rates
    When Get exchange rates
    Then View rate for currency code "USD"
    And View rate for currency named "dolar amerykański"
    And View currencies with rates above 5
    And View currencies with rates below 3