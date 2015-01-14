Feature: E2E Test
  Scenario: testing translation for new employee test

    Given I am on Google home page
    When I search 'google translate' text
    And I open result page in other browser's session
