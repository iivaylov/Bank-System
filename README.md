




AllianzBank:
Username: toronylis 
	pass: 97894m
Username: inaten
	pass: nxn9td29
Username: ingtitopt
	pass: uv378we9

CCBank:
Username: nogropet
	pass: 5366uv3s
Username: taima
	pass: j8939f5y
		changed: abc12345
Username: omonewp
	pass: sn3yh82d

### Sample Input

```none
CreateBank AllianzBank Frankfurt Germany St.AshurstWood12 1321
CreateBank CCBank London UnitedKingdom St.RamsayView101 3512
ShowBankInfo AllianzBank
ShowBankInfo CCBank
ShowAllBankUsers CCBank
ShowAllBankUsers AllianzBank
RegisterUserInBank AllianzBank toronylis 97894m Dawn Wagner Moscow Russia St.AshurstParade3 1001
Logout
RegisterUserInBank AllianzBank inaten nxn9td29 Calvin Smith Sofia Bulgaria St.SkiptonGarden 3512
Logout
RegisterUserInBank AllianzBank ingtitopt uv378we9 Deborah Sanchez WashingtonDC UnitedStates St.ShanklinAvenue 4332
Logout
ChangeUserPassword AllianzBank ingtitopt uv378we9 abc123456
RegisterUserInBank CCBank nogropet 5366uv3s Joshua Knight Frankfurt Germany St.HampsteadCrest34 3211
Logout
RegisterUserInBank CCBank taima j8939f5y Michael Carter Bucharest Romania St.GilmortonAvenue 9920
Logout
RegisterUserInBank CCBank omonewp sn3yh82d Tina Obrien Warsaw CzechRepublic St.HospitalHeath22 7092
Logout
Login CCBank nogropet 5366uv3s
OpenBankAccount CCBank nogropet Checking EUR
OpenBankAccount CCBank nogropet Credit USD
ShowHoleBankUserInfo CCBank nogropet
DepositMoneyInBankAccount CCBank nogropet 1 20000.00 
DepositMoneyInBankAccount CCBank nogropet 2 100000.00 
WithdrawMoneyFromBankAccount AllianzBank nogropet 2 40000.00
ShowHoleBankUserInfo CCBank nogropet
Logout
Login CCBank omonewp sn3yh82d
OpenBankAccount CCBank omonewp Checking USD
OpenBankAccount CCBank omonewp Credit BGN
ShowHoleBankUserInfo CCBank omonewp
DepositMoneyInBankAccount CCBank omonewp 3 500000.00 
DepositMoneyInBankAccount CCBank omonewp 4 100000.00 
WithdrawMoneyFromBankAccount CCBank omonewp 3 250000.00
WithdrawMoneyFromBankAccount CCBank omonewp 4 75000.00
TransferMoneyToBankAccount CCBank omonewp 3 CCBank nogropet 1 70000.00
TransferMoneyToBankAccount CCBank omonewp 4 CCBank omonewp 4 5000.00
ShowHoleBankUserInfo CCBank omonewp
Logout
Login CCBank taima j8939f5y
ShowUserPassword CCBank taima
ChangeUserPassword CCBank taima j8939f5y abc12345
AmILoggedin
ShowWhereIamLoggedin
Login AllianzBank toronylis 97894m
Logout
Login AllianzBank toronylis 97894m
OpenBankAccount AllianzBank toronylis Savings GBP
OpenBankAccount AllianzBank toronylis Checking EUR
OpenBankAccount AllianzBank toronylis Credit RUB
ShowHoleBankUserInfo AllianzBank toronylis
DepositMoneyInBankAccount AllianzBank toronylis 5 5000.00 
DepositMoneyInBankAccount AllianzBank toronylis 6 12000.00 
DepositMoneyInBankAccount AllianzBank toronylis 7 50000.00
WithdrawMoneyFromBankAccount AllianzBank toronylis 6 1000.00
WithdrawMoneyFromBankAccount AllianzBank toronylis 7 27500.00
ShowHoleBankUserInfo AllianzBank toronylis
Logout
Login AllianzBank inaten nxn9td29
OpenBankAccount AllianzBank inaten Checking CZK
OpenBankAccount AllianzBank inaten Checking RON
DepositMoneyInBankAccount AllianzBank inaten 8 7000.00 
DepositMoneyInBankAccount AllianzBank inaten 9 50500.00 
ShowHoleBankUserInfo AllianzBank inaten
Logout
Login AllianzBank ingtitopt uv378we9
OpenBankAccount AllianzBank ingtitopt Credit USD
OpenBankAccount AllianzBank ingtitopt Savings GBP
OpenBankAccount AllianzBank ingtitopt Checking EUR
DepositMoneyInBankAccount AllianzBank ingtitopt 10 10000.00
DepositMoneyInBankAccount AllianzBank ingtitopt 11 23340.00 
DepositMoneyInBankAccount AllianzBank ingtitopt 12 17500.00 
ShowHoleBankUserInfo AllianzBank ingtitopt
CloseBankAccount CCBank nogropet 1
ShowUserAccounts CCBank nogropet
Logout
Login CCBank taima j8939f5y
AmILoggedin
Login CCBank taima abc12345
DeleteUserFromBank CCBank taima
AmILoggedin
ShowAllBankUsers CCBank
ShowAllBankUsers AllianzBank
Exit
```




### Sample Output

```none
Bank AllianzBank created successfully!
Bank CCBank created successfully!
Bank name: AllianzBank
City: Frankfurt
Country: Germany
Street: St.AshurstWood12
Zip code: 1321
Bank name: CCBank
City: London
Country: United Kingdom
Street: St.RamsayView101
Zip code: 3512
---USERS IN BANK { CCBank }---
### There are no users in the bank! ###
---USERS IN BANK { AllianzBank }---
### There are no users in the bank! ###
User toronylis registered successfully!
You logged out!
User inaten registered successfully!
You logged out!
User ingtitopt registered successfully!
You logged out!
You are not logged in! Please login first!
User nogropet registered successfully!
You logged out!
User taima registered successfully!
You logged out!
User omonewp registered successfully!
You logged out!
User nogropet successfully logged in!
Account 1 created successfully!
Account 2 created successfully!
--- User: { Joshua }---
Username: nogropet
First name: Joshua
Last name: Knight
City: Frankfurt
Country: Germany
Street: St.HampsteadCrest34
Zip code: 3211

### ACCOUNTS ###
Account --- 1 ---
Bank: CCBank
Account ID: 1
User: nogropet
Account Type: Checking
Currency: EUR
Balance: 0,00

Account --- 2 ---
Bank: CCBank
Account ID: 2
User: nogropet
Account Type: Credit
Currency: USD
Balance: 0,00
Joshua, you have successfully deposited money in your bank account!
Joshua, you have successfully deposited money in your bank account!
User with this username does not exist!
--- User: { Joshua }---
Username: nogropet
First name: Joshua
Last name: Knight
City: Frankfurt
Country: Germany
Street: St.HampsteadCrest34
Zip code: 3211

### ACCOUNTS ###
Account --- 1 ---
Bank: CCBank
Account ID: 1
User: nogropet
Account Type: Checking
Currency: EUR
Balance: 20000,00

Account --- 2 ---
Bank: CCBank
Account ID: 2
User: nogropet
Account Type: Credit
Currency: USD
Balance: 100000,00
You logged out!
User omonewp successfully logged in!
Account 3 created successfully!
Account 4 created successfully!
--- User: { Tina }---
Username: omonewp
First name: Tina
Last name: Obrien
City: Warsaw
Country: Czech Republic
Street: St.HospitalHeath22
Zip code: 7092

### ACCOUNTS ###
Account --- 1 ---
Bank: CCBank
Account ID: 3
User: omonewp
Account Type: Checking
Currency: USD
Balance: 0,00

Account --- 2 ---
Bank: CCBank
Account ID: 4
User: omonewp
Account Type: Credit
Currency: BGN
Balance: 0,00
Tina, you have successfully deposited money in your bank account!
Tina, you have successfully deposited money in your bank account!
Tina, you have successfully withdrawn money from your bank account!
Tina, you have successfully withdrawn money from your bank account!
Tina, you have successfully transferred 70000,00 USD to Joshua, exchange rate is 0.94 between USD and EUR!
You can't transfer money to the same account!
--- User: { Tina }---
Username: omonewp
First name: Tina
Last name: Obrien
City: Warsaw
Country: Czech Republic
Street: St.HospitalHeath22
Zip code: 7092

### ACCOUNTS ###
Account --- 1 ---
Bank: CCBank
Account ID: 3
User: omonewp
Account Type: Checking
Currency: USD
Balance: 180000,00

Account --- 2 ---
Bank: CCBank
Account ID: 4
User: omonewp
Account Type: Credit
Currency: BGN
Balance: 25000,00
You logged out!
User taima successfully logged in!
Password for user taima is { j8939f5y }
Password changed successfully for user taima
You are logged in!
You are logged in to CCBank bank as taima user.
User taima is logged in! Please log out first!
You logged out!
User toronylis successfully logged in!
Account 5 created successfully!
Account 6 created successfully!
Account 7 created successfully!
--- User: { Dawn }---
Username: toronylis
First name: Dawn
Last name: Wagner
City: Moscow
Country: Russia
Street: St.AshurstParade3
Zip code: 1001

### ACCOUNTS ###
Account --- 1 ---
Bank: AllianzBank
Account ID: 5
User: toronylis
Account Type: Savings
Currency: GBP
Balance: 0,00

Account --- 2 ---
Bank: AllianzBank
Account ID: 6
User: toronylis
Account Type: Checking
Currency: EUR
Balance: 0,00

Account --- 3 ---
Bank: AllianzBank
Account ID: 7
User: toronylis
Account Type: Credit
Currency: RUB
Balance: 0,00
Dawn, you have successfully deposited money in your bank account!
Dawn, you have successfully deposited money in your bank account!
Dawn, you have successfully deposited money in your bank account!
Dawn, you have successfully withdrawn money from your bank account!
Dawn, you have successfully withdrawn money from your bank account!
--- User: { Dawn }---
Username: toronylis
First name: Dawn
Last name: Wagner
City: Moscow
Country: Russia
Street: St.AshurstParade3
Zip code: 1001

### ACCOUNTS ###
Account --- 1 ---
Bank: AllianzBank
Account ID: 5
User: toronylis
Account Type: Savings
Currency: GBP
Balance: 5000,00

Account --- 2 ---
Bank: AllianzBank
Account ID: 6
User: toronylis
Account Type: Checking
Currency: EUR
Balance: 11000,00

Account --- 3 ---
Bank: AllianzBank
Account ID: 7
User: toronylis
Account Type: Credit
Currency: RUB
Balance: 22500,00
You logged out!
User inaten successfully logged in!
Account 8 created successfully!
Account 9 created successfully!
Calvin, you have successfully deposited money in your bank account!
Calvin, you have successfully deposited money in your bank account!
--- User: { Calvin }---
Username: inaten
First name: Calvin
Last name: Smith
City: Sofia
Country: Bulgaria
Street: St.SkiptonGarden
Zip code: 3512

### ACCOUNTS ###
Account --- 1 ---
Bank: AllianzBank
Account ID: 8
User: inaten
Account Type: Checking
Currency: CZK
Balance: 7000,00

Account --- 2 ---
Bank: AllianzBank
Account ID: 9
User: inaten
Account Type: Checking
Currency: RON
Balance: 50500,00
You logged out!
User ingtitopt successfully logged in!
Account 10 created successfully!
Account 11 created successfully!
Account 12 created successfully!
Deborah, you have successfully deposited money in your bank account!
Deborah, you have successfully deposited money in your bank account!
Deborah, you have successfully deposited money in your bank account!
--- User: { Deborah }---
Username: ingtitopt
First name: Deborah
Last name: Sanchez
City: Washington DC
Country: United States
Street: St.ShanklinAvenue
Zip code: 4332

### ACCOUNTS ###
Account --- 1 ---
Bank: AllianzBank
Account ID: 10
User: ingtitopt
Account Type: Credit
Currency: USD
Balance: 10000,00

Account --- 2 ---
Bank: AllianzBank
Account ID: 11
User: ingtitopt
Account Type: Savings
Currency: GBP
Balance: 23340,00

Account --- 3 ---
Bank: AllianzBank
Account ID: 12
User: ingtitopt
Account Type: Checking
Currency: EUR
Balance: 17500,00
You can only close an account for yourself!
You can only view your own accounts!
You logged out!
Wrong username or password!
You are not logged in!
User taima successfully logged in!
User taima deleted successfully!
You are not logged in!
---USERS IN BANK { CCBank }---
1. nogropet
2. omonewp
---USERS IN BANK { AllianzBank }---
1. toronylis
2. inaten
3. ingtitopt
```
