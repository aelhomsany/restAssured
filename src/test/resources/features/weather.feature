Feature: get weather temperature for specific city

  Scenario Outline: Send get request to have the value for a city
    When send request to get temperature
      | query   | access_key   |
      | <query> | <access_key> |
    Then status code is "200"
    And temperatures printed out
    Examples:
      | query | access_key                       |
      | cairo | fa59fed84dfe86fb7c06065ecb670c6a |
      | luxor | fa59fed84dfe86fb7c06065ecb670c6a |