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
@tag
Feature: Register
  as a user, i want to access dashboard to register alta shop

  @register
  Scenario Outline: Create Register user page alta shop
    Given User open website alterra
    When User make sure website alterra has been loaded
    And User go to register page and define scenario <description>
    And User input form register <fullname>, <email> and <password> define <status>
    Then User click button register and verify based on <status>

    Examples: 
      | fullname | email      | password | status   | description                  |
      | Alterra  | alterraqa  | alta     | positive | register with add data valid |
      |          |            |          | negative | all field empty              |
      |          | alterraqa  | alta     | negative | fullname empty               |
      | Alterra  |            | alta     | negative | email empty                  |
      | Alterra  | alterra+qa |          | negative | password empty               |
