Feature: Check SS.COM website functionality

  @addSingleMemo
  Scenario Outline: Adding single Memo from different categories
    When I am on "SS" homepage
    When I open "<categoryName>" main category
    Then I check that I'm on the "<categoryName>" main category page
    When I open "<job>" subcategory
    Then I check that I'm on the "<job>" subcategory page
    And   I select "<position>" item in the list
    Then I store data for selected <position>
    And   I open "<number>" item in the list
    Then I add selected item to the memo
    Then I open "memo" page
    And Memo menu is showing "1" ad(s)
    And Memo page has "1" ad(s)
    Then I compare data for "<position>" ad(s) with stored data
    Examples:
      | categoryName | job           | position | number |
      | Vacancies    | Administrator | 1        | 1      |
      | Vacancies    | Baker         | 1        | 1      |


  @addMultiMemo
  Scenario Outline: Adding multi Memo from single category
    When I am on "SS" homepage
    When I open "<categoryName>" main category
    Then I check that I'm on the "<categoryName>" main category page
    When I open "<job>" subcategory
    And   I select "<position>" items in the list
    Then I store data for selected <position>
    Then I add selected items to the memo
    Then I open "memo" page
    And Memo menu is showing "<count>" ad(s)
    And Memo page has "<count>" ad(s)
    Then I compare data for "<position>" ad(s) with stored data
    Examples:
      | categoryName | job           | position  | count |
      | Vacancies    | Administrator | 1,2,3     | 3     |
      | Vacancies    | Administrator | 1,2,3,4,5 | 5     |


  @deleteSingleMemo
  Scenario Outline: Adding and deleting single Memo from different category
    When I am on "SS" homepage
    When I open "<categoryName>" main category
    Then I check that I'm on the "<categoryName>" main category page
    When I open "<job>" subcategory
    Then I check that I'm on the "<job>" subcategory page
    And   I select "<position>" item in the list
    And   I open "<number>" item in the list
    Then I add selected item to the memo
    Then I open "memo" page
    And Memo menu is showing "<number>" ad(s)
    And Memo page has "<number>" ad(s)
    When I delete all memo from the list
    Then Memo menu is empty
    Examples:
      | categoryName | job           | position | number |
      | Vacancies    | Administrator | 1        | 1      |


  @deleteMultiMemo
  Scenario Outline: Adding and deleting multi Memo from different categories
    When I am on "SS" homepage
    When I open "Vacancies" subcategory
    When I open "<role>" subcategory
    And   I select "<position>" items in the list
    Then I add selected items to the memo
    Then I open "memo" page
    And Memo menu is showing "<count>" ad(s)
    And Memo page has "<count>" ad(s)
    When I delete all memo from the list
    Then Memo menu is empty
    Examples:
      | role          | position | count |
      | Administrator | 1,2,3    | 3     |
      | Baker         | 1,2      | 2     |


  @memoMenuValidation
  Scenario Outline: Checking Memo menu behavior
    When I am on "SS" homepage
    When I open "<categoryName>" main category
    Then I check that I'm on the "<categoryName>" main category page
    When I open "<job>" subcategory
    Then I check that I'm on the "<job>" subcategory page
    And  Memo menu is empty
    And   I select "<position>" item in the list
    And   I open "<number>" item in the list
    Then I add selected item to the memo
    Then Memo menu isn't empty
    Examples:
      | categoryName | job   | position | number |
      | Vacancies    | Baker | 1        | 1      |


#   @mock test contains basic steps for storing data as object that is possible to
#   use for validation extracting items by unique id/.
  @mock
  Scenario: Collecting data from web site
    When I am on "SS" homepage
    When I open "Vacancies" main category
    Then I store data for main category
    When I open "Administrator" subcategory
    Then I store data for subcategory
