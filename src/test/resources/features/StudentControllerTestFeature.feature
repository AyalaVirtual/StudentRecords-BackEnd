Feature: Rest API functionalities

  Scenario: User able to add, edit, and remove student
    Given A list of students are available
    When I add a student to my list
    Then The student is added
    When I get a specific student
    Then The specific student is available
    When I edit a student from my list
    Then The student content is edited
    When I remove a student from my list
    Then The student is removed