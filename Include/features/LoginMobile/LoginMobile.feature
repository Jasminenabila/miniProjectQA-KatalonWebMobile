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
Feature: Login Mobile
  as a user, i want to access login on apk alta shop

  @registermobile
  Scenario Outline: Login user page alta shop on apk
    Given User launch app login
    When User has been loaded app login
    And User click button icon login and define <description>
    And User fill form login on apk <email> and <password>
    Then User click button login on apk and verify based on <status>

    Examples: 
      | email                 | password | status   | descriptive           |
      | someone@mail.com      |   123123 | positive | login with data valid |
      |                       |          | negative | all field empty       |
      | alterraqa@alterra.com | alta     | negative | not registered        |
      |                       | alta*()  | negative | email invalid format  |
      | someone@mail.com      |          | negative | password empty        |
