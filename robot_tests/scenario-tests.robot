*** Settings ***
Library            SeleniumLibrary
Library            Process
Suite Teardown     Close All Browsers


*** Variables ***
${LOGIN URL}      https://my-user-accounts.herokuapp.com/
${BROWSER}        chrome


*** Test Cases ***
Scenario 1
    Open Browser  ${LOGIN URL} 	${BROWSER} 
    Click Element     id:show-form-button
    Input Text        id:input_id          1  
    Input Text        id:input_name        Generic Name
    Input Text        id:input_phone       999999999 
    Input Text        id:input_email       genericname@company.com
    Input Text        id:input_address     Generic Street 42 Earth
    Input Text        id:input_country     Navarro
    Input Text        id:input_department  T21R
    Click Element     id:create-new-button
    ${response}    Get Text    id:success-message
    Should Be Equal As Strings    ${response}    User Accounts Created Successfully
    
Scenario 2
    Open Browser  ${LOGIN URL} 	${BROWSER} 
    Click Element     id:show-form-button
    Input Text        id:input_id          2 
    Input Text        id:input_name        Kuill
    Input Text        id:input_phone       99999999999999
    Input Text        id:input_email       genericname@company.com
    Input Text        id:input_address     Generic Street 42 Hoth
    Input Text        id:input_country     Arvala
    Input Text        id:input_department  T21R
    Click Element     id:create-new-button
    ${response}    Get Text    id:error-message
    Should Be Equal As Strings    ${response}    phone size must be between 9 and 12
    
Scenario 3
    Open Browser  ${LOGIN URL} 	${BROWSER} 
    Click Element     id:show-form-button
    Input Text        id:input_id          1
    Input Text        id:input_name        Generic Name
    Input Text        id:input_phone       222222222
    Input Text        id:input_email       genericname@company.com
    Input Text        id:input_address     Generic Street 42 Earth
    Input Text        id:input_country     Navarro
    Input Text        id:input_department  T21R
    Click Element     id:create-new-button
    ${response}    Get Text    id:success-message
    Should Be Equal As Strings    ${response}    User Accounts Updated Successfully
    
Scenario 4
    Open Browser  ${LOGIN URL} 	${BROWSER} 
    Click Element     id:delete-button-id-1
    ${response}    Get Text    id:success-message
    Should Be Equal As Strings    ${response}    User Accounts Deleted Successfully
    