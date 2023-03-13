Feature: Itinerary search
  A feature to search itinerary

  Background: order search
    Given a user is on the home page
    When  a user navigates to the Login page using "https://adactinhotelapp.com/"
    And a user enter "sthembiso16" and "3755RP"
    And a user clicks the login button

    Scenario: Order search successful
      And a user navigate to the Booked Itinerary
      And a user search for Itinerary using orderId
      Then order is displayed successfully