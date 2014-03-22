Camel Router Project for Blueprint (OSGi)
=========================================

1. 	Build this project
    	mvn clean install

2. 	Deploy and start the example in OSGi container 
		osgi:install -s mvn:com.ggl.esb.osgi.rs/osgi.blueprint.rs.demo

3.	Test the exampl, run the following curl commands:

   	3.1 POST  
  		$ curl -X POST -T src/main/resources/client/add_customer.xml -H "Content-Type: text/xml" http://localhost:19191/customerservice/customers
  		
  		<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Customer><id>124</id><name>Jack</name></Customer>

   	3.2 GET
  		curl http://localhost:19191/customerservice/customers/123
 
	3.3 PUT
  		 curl -X PUT -T src/main/resources/client/update_customer.xml -H "Content-Type: text/xml" http://localhost:19191/customerservice/customers

	
	3.4 DELETE   
		curl -X DELETE http://localhost:19191/customerservice/customers/123
		
4.	Explanation
	self explained.