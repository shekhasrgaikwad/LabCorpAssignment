Feature: Create user

Scenario Outline: Verify create user api
Given I have the user details <username>
When I create the user using POST request with <id>,<firstName>,<lastName>,<email>,<password>,<phone>,<userStatus>
Then I verify the user is created using GET request with <id>,<username>,<firstName>,<lastName>,<email>,<password>,<phone>,<userStatus>
Examples:
|id|username|firstName|lastName|email|password|phone|userStatus|
|43433|"testUser-shekhar"|"FName"|"LName"|"mymail@gmail.com"|"password1"|"2223333"|0|

