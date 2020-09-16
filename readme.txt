This is the Java coding assignment for the Northlands Property folks.

This mini "Find the most affordable hotel" application has been setup as a basic Java Spring 5 REST WebService 
which runs within an embedded Tomcat webserver, running locally in development mode on port:8080.

In order to build and run this project:

1) Import the "pom.xml" file as a Maven project from your IDE.
2) Run the CheapestHotelServiceApplication as a Java application.
3) Once running, open up Postman and use the following URI:

http://localhost:8080/hotels/latitude/{lat}/longitude/{lng}/checkIn/{checkIn}/checkOut/{checkOut}

	- where "{lat}" represents the latitude coordinates for your target airport (such as 49.196691 for Vancouver)
	- where "{lng}" represents the longitude coordinates for your target airport (such as -123.181512 for Vancouver)
	- where "{checkIn}" represents the date you want to check in using the "YYYY-MM-DD" date format.
	- where "{checkOut}" represents the date you want to check out using the "YYYY-MM-DD" date format.
	
at that point the application will then interact with the external "Amadeus Hotel Offer" service that searches for all hotels 
within a 10 Km radius of the target airport's geolocation coorinates (please note that this 10 Km radius was hardcoded in the 
application) and the code will then return a JSON string representing the best most affordable hotel offering returned.

Here are a few additional design notes:

1) Data returned by this application uses a different domain entity class hierarchy than what was originally returned by the 
Amadeus service. This was done on purpose to show that not only could the data be converted but it also showned some possible 
JPA annotations in the various domain entity classes should the need to hook the data up to a database was to ever surface. 
With that said, these annotations are commented. I guess I could have tried to use an in-memory database to fullfil this need. 
But I ran low on time. Sorry ...
   
2) There are some "for" loops used in the code that I really did not want to use. But I was having issues with the data objects 
returned by the  "Amadeus Hotel Offer" service in terms of using lambda expressions instead of "for" loops. 
   
3) Finally the Amadeus "Client-ID" and "Client-Secret" values checked into this GitHub repo have been altered and are not valid.
One would need to use their own Amadeus crenditials and/or depending on who you are, I could supplied you with the valid values.