Feature: As a user I want to be able add/remove products from my wishlist

  Background: I open browser and I navigate to homePage
    Given I navigate to Emag home page
    And  I log in into application with "<YOUR_EMAG_EMAIL>" email and "<YOUR_EMAG_PASSWORD>" password

  #@RemoveFromWhishList
  Scenario: Add product to wishlist
    Given I navigate to the "https://www.emag.ro/monitor-led-pls-samsung-23-5-wide-fhd-hdmi-flicker-free-ls24f350fh-negru-ls24f350fhuxen/pd/DX74V3BBM" product
    When I click on product option "Adauga la Favorite"
    #Then I verify that "Produsul a fost adaugat la favorite" messgae is displayed // not stable step
    Then I verify that added to favorites icon is displayed
    Given I open Favorite product page
    And I select "Monitor LED PLS Samsung 23.5" product from wishlist
    Then I press delete button on wishlist page

  Scenario: Verify wishlist content
    Given I open Favorite product page
    Then I veirfy if wishlist contains the following products:
      | Mouse gaming Razer Mamba Wireless, Negru          |
      | Telefon mobil Samsung Galaxy S7, 32GB, 4G, Silver |