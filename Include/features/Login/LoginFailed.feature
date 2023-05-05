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
Feature: Login
  as a user, i want to access login dashboard alta shop

  @loginfailed
  Scenario Outline: login failed
    Given User open website alta shop
    When User make sure website alta shop has been successfully loaded
    And User go to redirect login to scenario failed and define <description>
    And User input data scenario failed based on data <email> and <password>
    Then User click button login

    Examples: 
      | email                 | password | description          |
      | jasmineqa@alterra.com | batagor  | login not registered |
      |                       |   123123 | email empty          |
      | someone@mail.com      |          | password empty       |
