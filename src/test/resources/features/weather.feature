Feature: get weather temperature for specific city

  Scenario Outline: Send get request to have the value for a city
    When send request to get "<city>" temperature
    Then status code is "200"
    And temperatures printed out
    Examples:
      | city  |
      | cairo |
      | luxor |