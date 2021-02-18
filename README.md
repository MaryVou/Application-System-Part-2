# Application-System-Part-2
This is the second part of a project made for "Distributed Systems" course. It contains a spring boot app serving as a web app, using the rest api of the first part of the project.

# Installation

## For eclipse:
* Download the code
* Import project as existing maven project
* Run
* Connect to localhost:9090 with "It_Employee1" as username and "pass123" as password

## For terminal
* Clone the code
* Run the command: mvn spring-boot:run
* Connect to localhost:9090 with "It_Employee1" as username and "pass123" as password


**Note** that a full list of users exists in data.sql under resources of the first application and everyone uses pass123 as password

**Also**, if you wish to run both applications on a local level, you will have to change the urls in the requests in order to reach localhost:8080 and not "http://themelicompany.cloudns.cl"
