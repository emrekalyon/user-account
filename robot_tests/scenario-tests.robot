*** Settings ***
Library            SeleniumLibrary
Library            Process
Suite Teardown     Close All Browsers


*** Variables ***
${LOGIN URL}      https://my-user-accounts.herokuapp.com/
${BROWSER}        chrome


*** Test Cases ***
Scenario 1
    Given User can access the API
    When User fill the field id with "1"
    And User fill the field "name" with "Generic Name"
    And User fill the field "phone" with "999999999"
    And User fill the field "email" with "genericname@company.com"
    And User fill the field "address" with "Generic Street 42 Earth"
    And User fill the field "country" with "Navarro"
    And User fill the field "department" with "T21R"
    And User send this data to API (CREATE)
    Then API should return Ok Created with a success message        
    
Scenario 2
    Given User can access the API
    When User fill the field id with "2"
    And User fill the field "name" with "Kuill"
    And User fill the field "phone" with "99999999999999"
    And User fill the field "email" with "genericname@company.com"
    And User fill the field "address" with "Generic Street 42 Hoth"
    And User fill the field "country" with "Arvala"
    And User fill the field "department" with "T21R"
    And User send this data to API (CREATE)
    Then API should return error with a message
    
Scenario 3
    Given User can access the API
    When User fill the field id with "1"
    And User fill the field "name" with "Generic Name"
    And User fill the field "phone" with "222222222"
    And User fill the field "email" with "genericname@company.com"
    And User fill the field "address" with "Generic Street 42 Earth"
    And User fill the field "country" with "Navarro"
    And User fill the field "department" with "T21R"
    And User send this data to API (UPDATE)
    Then API should return Ok with a success message    
    
Scenario 4
    Given User can access the API
    When User wants to delete the account with id: "1"
    And User send id: "1" to API (DELETE)
    Then API should return no content with a success message    
    
*** Keywords ***
Given User can access the API
    Open Browser  ${LOGIN URL} 	${BROWSER} 
When User fill the field id with "${val}"
    Click Element     id:show-form-button   
    Input Text        id:input_id          ${val}
And User fill the field "name" with "${name}"    
    Input Text        id:input_name        ${name}
And User fill the field "phone" with "${phone}"
    Input Text        id:input_phone       ${phone} 
And User fill the field "email" with "${email}"    
    Input Text        id:input_email       ${email}
And User fill the field "address" with "${address}"    
    Input Text        id:input_address     ${address}
And User fill the field "country" with "${country}"    
    Input Text        id:input_country     ${country}
And User fill the field "department" with "${department}"    
    Input Text        id:input_department  ${department}
And User send this data to API (CREATE)        
    Click Element     id:create-new-button
Then API should return Ok Created with a success message 
    ${response}    Get Text    id:success-message
    Should Be Equal As Strings    ${response}    User Accounts Created Successfully

Then API should return error with a message
    ${response}    Get Text    id:error-message
    Should Be Equal As Strings    ${response}    phone size must be between 9 and 12   
    
And User send this data to API (UPDATE)
    Click Element     id:create-new-button
Then API should return Ok with a success message    
    ${response}    Get Text    id:success-message
    Should Be Equal As Strings    ${response}    User Accounts Updated Successfully

When User wants to delete the account with id: "${id}"
    ${temp_text}    Get Text    id:delete-button-id-${id}
   
And User send id: "${id}" to API (DELETE)  
    Click Element     id:delete-button-id-${id}
    
Then API should return no content with a success message        
    ${response}    Get Text    id:success-message
    Should Be Equal As Strings    ${response}    User Accounts Deleted Successfully
    