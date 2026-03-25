Feature: Validating Place API's

@AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<Name>" ,"<Address>" , <Accuracy> , "<PhoneNumber>", "<Language>", "<Website>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<Name>" using "getPlaceAPI"

    Examples: 
      | Name   | Address | Accuracy | PhoneNumber | Language | Website        |
      | Costco | Arizona |       50 |  6785432561 | English  | www.costco.com |
    #  | Home   | Ponda   |       10 |  7896542341 | Konkani  | www.home.com   |

@DeletePlace
  Scenario: Verify if places are sucessfuly getting deleted using DeletePlaceAPI
    Given Place is added successfully
    When user calls "deletePlaceAPI" with "DELETE" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
