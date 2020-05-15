1. The requirements have been implemented as two mircoservices with spring boot.

2. They are customer service and account service.  Here is how to make it run:

	a. Install a local mysql

	b. Unzip the citi_cc_wade.zip

	c. Change the db info in application.properties

	d. Go to customerService or accountService and start with ./gradlew bootRun.
	
3. The customer service can be tested with tools like postman with the following URLs:

	a. Create a new customer
		http://localhost:3001/customer/new
		
		And add the test data in the request body:
			
			{
			   "firstName": "Mary",
			   "lastName": "Smith",
			   "address" : "1001 Main Street",
			   "phoneNumber" : "9720000000",
			   "ssn":"111-11-1111"
			}
	b. Retrieve all customers
		http://localhost:3001/customers
	
	c. Retrieve one customer with customer id
		http://localhost:3001/customer/<id>

3. The account service can be tested with tools like postman with the following URLs:

	a. Create a new account
		http://localhost:3002/account/new
		
		And add the test data in the request body:
			
			{
			    "type": "Saving",
			    "customerId": "2",
			    "amount": "1999.99"
			}
	b. Retrieve all accounts
		http://localhost:3002/accounts
	
	c. Retrieve one account with account id
		http://localhost:3002/account/1
		