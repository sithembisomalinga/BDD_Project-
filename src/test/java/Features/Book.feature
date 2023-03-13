Feature: Hotel Booking
  A feature to book for a hotel

  Background:
    Given a user is on the home page
    When  a user navigates to the Login page using "https://adactinhotelapp.com/"
    And a user enter "sthembiso16" and "3755RP"
    And a user clicks the login button

  Scenario Outline: Booking successful
    And a user fill in search form with details including "<checkInDate>" and "<checkOutDate>"
    And a user clicks the search button
    And a user select hotel
    And a user clicks the continue button
    And a user enters Booking details"<firstname>", "<lastname>", "<address>", "<cardNumber>", "<cardType>", "<expiryMonth>", "<expiryYear>", "<cvvNumber>"
    And a user clicks the book now button
    Then a user booked successful

    Examples:
      | firstname | lastname | address | cardNumber       | cardType | expiryMonth | expiryYear | cvvNumber | checkInDate | checkOutDate |
      | Sithembiso     | Kubheka  | Johannesburg | 1111111111111111 | VISA     | June       | 2022       | 111       | 06/03/2022  | 08/03/2022   |

  Scenario Outline: Booking unsuccessful
    And a user fill in search form with details including "<checkInDate>" and "<checkOutDate>"
    And a user clicks the search button
    And a user select hotel
    And a user clicks the continue button
    And a user enters Booking details"<firstname>", "<lastname>", "<address>", "<cardNumber>", "<cardType>", "<expiryMonth>", "<expiryYear>", "<cvvNumber>"
    And a user clicks the book now button
    Then a user booking is unsuccessful


    Examples:
      | firstname | lastname | address | cardNumber  | cardType | expiryMonth | expiryYear | cvvNumber | checkInDate | checkOutDate |
      |    Sithembiso    | Kubheka   | Johannesburg  |  1111111111111111 | VISA     | June       | 2022       | 111      | 06/03/2022  | 08/03/2022   |