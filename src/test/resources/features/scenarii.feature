#see https://cucumber.io/docs/gherkin/reference/
Feature: Todo API

  Scenario Outline: Find by id should return correct entity
    Given table todo contains data:
      |id                                  |title  |description  |completed  |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false      |
      |18a281a6-0882-4460-9d95-9c28f5852db1|title 2|description 2|false      |
    And id = "<id>"
    When call find by id
    Then The http status is 200
    And the returned todo has properties title="<title>",description="<description>" and completed="<completed>"
    Examples:
      | id                                   | title   | description   | completed  |
      | 17a281a6-0882-4460-9d95-9c28f5852db1 | title 1 | description 1 | false      |
      | 18a281a6-0882-4460-9d95-9c28f5852db1 | title 2 | description 2 | false      |


  Scenario Outline: Find by non existing id should return not found
    Given table todo contains data:
      |id                                  |title  |description  |completed  |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false      |
      |18a281a6-0882-4460-9d95-9c28f5852db1|title 2|description 2|false      |
    And id = "<bad_id>"
    When call find by id
    Then The http status is 404
    Examples:
      |bad_id                              |
      |27a281a6-0882-4460-9d95-9c28f5852db1|
      |28a281a6-0882-4460-9d95-9c28f5852db1|

  Scenario Template: Find all should return correct list
    Given table todo contains data:
      |id                                  |title  |description  |completed  |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false      |
      |18a281a6-0882-4460-9d95-9c28f5852db1|title 2|description 2|false      |
    When call find all
    Then The http status is 200
    And the returned list has 2 elements
    And that list contains values: title="<title>" and  description="<description>" and completed="<completed>":
    Examples:
      | title   | description   | completed  |
      | title 1 | description 1 | false      |
      | title 2 | description 2 | false      |

  Scenario: delete an existing todo
    Given table todo contains data:
      |id                                  |title  |description  |completed  |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false      |
      |18a281a6-0882-4460-9d95-9c28f5852db1|title 2|description 2|false      |
    When id = "17a281a6-0882-4460-9d95-9c28f5852db1"
    When call delete
    Then The http status is 204

  Scenario: delete a non existing todo
    Given table todo contains data:
      |id                                  |title  |description  |completed   |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false      |
      |18a281a6-0882-4460-9d95-9c28f5852db1|title 2|description 2|false      |
    When id = "27a281a6-0882-4460-9d95-9c28f5852db1"
    When call delete
    Then The http status is 404

  Scenario: complete an existing todo
    Given table todo contains data:
      |id                                  |title  |description  |completed  |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false      |
      |18a281a6-0882-4460-9d95-9c28f5852db1|title 2|description 2|false      |
    And id = "17a281a6-0882-4460-9d95-9c28f5852db1"
    When call complete
    Then The http status is 202
    And the completed todo has property completed="true"

  Scenario: complete an non existing todo
    Given table todo is empty
    When id = "27a281a6-0882-4460-9d95-9c28f5852db1"
    When call complete
    Then The http status is 404

  Scenario Outline: add should add toto
    Given table todo contains data:
      |id                                  |title  |description  |completed  |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false      |
      |18a281a6-0882-4460-9d95-9c28f5852db1|title 2|description 2|false      |
    And title = "<title>"
    And  description = "<description>"
    When call add todo
    Then The http status is 201
    And the created todo has properties title="<title>", description="<description>", completed="<completed>"
    Examples:
      |title    |description    |completed  |
      |title 11 |description 11 |false      |

  Scenario: add todo with an existing title should fail
    Given table todo contains data:
      |id                                  |title  |description  |completed |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false     |
      |18a281a6-0882-4460-9d95-9c28f5852db1|title 2|description 2|false     |
    When title = "title 1"
    And  description = "description 1.1"
    When call add todo
    Then The http status is 409

  Scenario Outline: update an existing todo should succeed
    Given table todo contains data:
      |id                                  |title  |description  |completed |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false     |
      |18a281a6-0882-4460-9d95-9c28f5852db1|title 2|description 2|false     |
    And  id = "<id>"
    And  title = "<title>"
    And  description = "<description>"
    When call update todo
    Then The http status is 202
    And the updated todo has properties title="<title>", description="<description>", completed="<completed>"
    Examples:
      |id                                   |title     |description     |completed|
      |17a281a6-0882-4460-9d95-9c28f5852db1 |title 1.1 |description 1.1 | false   |

  Scenario: update a non existing todo should fail
    Given table todo is empty
    And  id = "17a281a6-0882-4460-9d95-9c28f5852db1 "
    And  title = "title 1"
    And  description = "description 1"
    When call update todo
    Then The http status is 404

  Scenario: add todo with title exceeding max size
    Given table todo is empty
    And title contains 81 characters
    And description contains 255 characters
    When call add todo
    Then The http status is 400

  Scenario: add todo with title less than min size
    Given table todo is empty
    And title contains 1 characters
    And description contains 255 characters
    When call add todo
    Then The http status is 400


  Scenario: add todo with description exceeding max size
    Given table todo is empty
    And title contains 50 characters
    And description contains 256 characters
    When call add todo
    Then The http status is 400



  Scenario: update todo with title exceeding max size
    Given table todo contains data:
      |id                                  |title  |description  |completed |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false     |
    And  id = "17a281a6-0882-4460-9d95-9c28f5852db1"
    And title contains 81 characters
    And description contains 255 characters
    When call update todo
    Then The http status is 400


  Scenario: update todo with less than min size
    Given table todo contains data:
      |id                                  |title  |description  |completed |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false     |
    And  id = "17a281a6-0882-4460-9d95-9c28f5852db1"
    And title contains 1 characters
    And description contains 255 characters
    When call update todo
    Then The http status is 400


  Scenario: update todo with description exceeding max size
    Given table todo contains data:
      |id                                  |title  |description  |completed |
      |17a281a6-0882-4460-9d95-9c28f5852db1|title 1|description 1|false     |
    And  id = "17a281a6-0882-4460-9d95-9c28f5852db1"
    And title contains 50 characters
    And description contains 256 characters
    When call update todo
    Then The http status is 400