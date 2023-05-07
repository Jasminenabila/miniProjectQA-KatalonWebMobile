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
@addproducts
Feature: Products
  as a user, i want to access product alta shop, include product details, add product cart

  Scenario: Add Products to cart
    Given User login before
    When User click button beli on dashboard alta shop
    And User verify badge add to cart
    And User Click icon cart
    Then User has been loaded page order and success input item to cart order
