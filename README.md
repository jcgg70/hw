# HW project

System that parses and sorts set of records on command line and by REST API

The system receives a text file as input on pipe, comma or space delimited format,
it reads the files and processes each record. A Person object is created from each
record as it is parsed and processed. I used a Simple Factory to do this.

Each person object is then added to a list and then sorted in three different ways.
The approached I used was to use a strategy pattern, by creating three different classes
that implement the Comparator interface. This is helpful for code reuse.

Finally the system is a REST API provider with a POST to add a single record, and three
GETs that reuse the sorting strategy previously mentioned. The response is in JSON.

* Input<br /><br />
Pipe-delimited file:  
LastName|FirstName|Gender|FavoriteColor|DateOfBirth<br /><br />
Comma-delimited file:  
LastName,FirstName,Gender,FavoriteColor,DateOfBirth<br /><br />
Space-delimited file:  
LastName FirstName Gender FavoriteColor DateOfBirth


* Output  
Sorted by gender  
Sorted by birth date  
Sorted by last  name

* REST API with the following endpoints:  
POST /records  
GET /records/gender  
GET /records/birthdate  
GET /records/name  

The output returns JSON

## Tecnologies Used
Java JDK 11.0.8  
Spring Boot framework  
Maven 3.5 (to build)  

## Note
This project is intended as a quick assignment and therefore it may be lacking robust
exception handling or additional unit test coverage.

## Installation
```
C:\Users\Juan\IdeaProjects\hw>mvn clean
C:\Users\Juan\IdeaProjects\hw>mvn package
```

## Usage
Start your jar with tomcat embedded with the following command and passing in file as argument
```
C:\Users\Juan\IdeaProjects\hw\target>java -jar hw-1.0.0.jar ../../../file1.csv
```

There are other ways to start the projec such as with mvn spring-boot:run or directly using
your favorite IDE 

