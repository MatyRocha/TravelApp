#Shortest Route Microservice

This service provide a query to get the shortest fly route from an Origin and a Destination

##_Build the project_
To build the project and perform the unit testing process you just need to execute, inside the project's root folder,  in a console the command
```
mvn clean package
```
This process will clean all precompiled classes, compile all the project, run all unit tests and create a distributable and executable 'jar' file.

Once the build is done then you can execute the microservice with the command

```
java -jar target/take_home_test-0.0.1-SNAPSHOT.jar
```
Finally you can use the microservice endpoint with the command

```
curl -H application/x-www-form-urlencoded -X GET 'http://localhost:8080/travel?origin=YYZ&destination=LAX'
```
or, if you don't have the 'curl' command, you need to open an Internet browser and go to the URL
```
http://localhost:8080/travel?origin=YYZ&destination=LAX
```
As a result you will get a json with an array of flights that you should catch.  All of them have a route, origin and destination airports and airline
```
[{"message":"Success","route":{"airline":"AC","origin":"YVR","destination":"FRA"},"airportOrigin":{"name":"Vancouver International Airport","city":"Vancouver","country":"Canada","iata3":"YVR","latitude":"49.19390106","longitude":"-123.1839981"},"airportDestination":{"name":"Frankfurt am Main International Airport","city":"Frankfurt","country":"Germany","iata3":"FRA","latitude":"50.0333333","longitude":"8.5705556"},"airline":{"name":"Air Canada","code2":"AC","code3":"ACA","country":"Canada"}},{"message":"Success","route":{"airline":"UA","origin":"FRA","destination":"JFK"},"airportOrigin":{"name":"Frankfurt am Main International Airport","city":"Frankfurt","country":"Germany","iata3":"FRA","latitude":"50.0333333","longitude":"8.5705556"},"airportDestination":{"name":"John F Kennedy International Airport","city":"New York","country":"United States","iata3":"JFK","latitude":"40.63980103","longitude":"-73.77890015"},"airline":{"name":"United Airlines","code2":"UA","code3":"UAL","country":"United States"}}]
```
