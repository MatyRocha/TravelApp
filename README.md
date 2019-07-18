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
