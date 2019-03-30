Feature: As a user I want to be able to log in into application

  Background: I open browser and I navigate to homePage
    Given I navigate to Emag home page

  Scenario: Login wit correct username and password - DO NOT remember credentials
    Given I navigate to the login page
    When I set "email" adress field to "<YOUR_EMAG_EMAIL>"
    And I press "Continua" button
    And I set "password" field to "<YOUR_EMAG_PASSWORD>"
    And I "UNCHECK" box "keep_email" option
    And I press "Continua" button after valid password
    And I open user menu
    Then I verify that user name "Vetisan Vasile" is displayed

  Scenario: Login with valid username and invalid password
    Given I navigate to the login page
    When I set "email" adress field to "<YOUR_EMAIL>"
    And I press "Continua" button
    And I set "password" field to "WrongPass"
    And I "UNCHECK" box "keep_email" option
    And I press "Continua" button after invalid password
    Then I verify that "Ai introdus gresit parola sau adresa de email. Te rog completeaza din nou." error message appears

  Scenario: Login with social media account - google account
    Given I navigate to the login page
    When I click on "Google" login option
    And I set "<YOUR_GOOGLE_EMAIL>" as google email
    And I press Next button on google login for password
    And I set "<YOUR_GOOGLE_PASSWORD>" as google password
    And I press Next button on google login form with window focus change
    And I open user menu
    Then I verify that user name "Vetisan Vasile" is displayed