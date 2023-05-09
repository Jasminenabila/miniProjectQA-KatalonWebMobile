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
@orders
Feature: Orders
  as a user, i want to orders products on alta shop

  Scenario: Add orders with qty -1 login
    Given User login before on order page
    When User click button beli on dashboard alta shop orders scenario
    And User verify badge add to cart on order page
    And User Click icon cart on order page and minus qty
    Then User verify orders is empty
#test jasmine
