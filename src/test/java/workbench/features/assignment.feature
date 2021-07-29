Feature: Assignment

  @blankImage
  Scenario Outline: Validate blank image

    Given I am on page "<url>"
    Then  I verify the blank image in slide bar

    Examples:
      | url        |
      | blankImage |


