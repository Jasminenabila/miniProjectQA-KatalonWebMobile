#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Register Mobile
  as a user, i want to access register on apk alta shop

  @registermobile
  Scenario Outline: Create Register user page alta shop on apk
    Given User launch app
    When User has been loaded app
    And User click button icon login and click hyperlink register and define <description>
    And User fill form register on apk alta shop <fullname>, <email> and <password> define <status>
    Then User click button register on apk and verify based on <status>

    Examples: 
      | fullname | email                  | password | status   | description                  |
      | alterra  | qa                     | alta     | positive | register with add data valid |
      |          |                        |          | negative | all field empty              |
      |          | alterraqa@alterra.com  | alta     | negative | fullname empty               |
      | Alterra  |                        | alta     | negative | email empty                  |
      | Alterra  | alterra+qa@alterra.com |          | negative | password empty               |
