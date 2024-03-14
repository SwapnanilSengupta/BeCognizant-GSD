Feature: Test GSD feature of One Cognizant
  Scenario: Get support options of different country
    Given User is on the BeCognizant page
    When I click on profile
    And I click OneCognizant
    Then I switch to OneCognizant window
    When I search GSD
    Then I switch to GSD frame
    And I verify the title message
    When I print all the languages in GSD
    Then I print the support options for India GSD
    And I randomly select the first country
    And I randomly select the second country