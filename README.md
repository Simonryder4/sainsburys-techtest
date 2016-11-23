# sainsburys-techtest
 A project built to fulfil the requirements of a technical test for developers applying for jobs at Sainsbury's.
 
## Synopsis
The requirement was to "Using best practice coding methods, build a console application that scrapes the Sainsbury’s grocery site - Ripe Fruits page and returns a JSON array of all the products on the page." 

## Prerequisites
#### Maven
Maven 3 or later will need to be installed.
See [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).
 
Environment variable MAVEN_HOME will need to be set.

#### Java
Java JDK version 7 or later will need to be installed.
See [http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

Environment variable JAVA_HOME will need to be set.

## Cloning the project
To download source code can be cloned from 
[https://github.com/Simonryder4/sainsburys-techtest.git](https://github.com/Simonryder4/sainsburys-techtest.git)

## Building the application
Open a command console and navigate to 
<directory path>/sainsburys-techtest
This directory should contain the downloaded source including the maven build file pom.xml.
Execute this command:

`mvn clean package`

This command will build build the application. run all tests and package the application ready for execution.

## Tests
To test the application without packaging it:
Open a command console and navigate to 
<directory path>/sainsburys-techtest
Execute this command:

`mvn test`

## Running the application
Open a command console and navigate to 
<directory path>/sainsburys-techtest
having build the application as indicated above execute this command:

`java -cp target\sainsburys-techtest-1.0.0-SNAPSHOT-jar-with-dependencies.jar uk.co.usryders.sainsburys.techtest.TechTestRunner`

You should see an output that starts something like this:

    {
      "results" : [ {
        "title" : " Sainsbury's Apricot Ripe & Ready x5 ",
        "size" : "38.3kb",
        "description" : "Buy Sainsbury's Apricot Ripe & Ready x5 online from Sainsbury's, the same     great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.",
        "unit_price" : 3.50
      }, {
        "title" : " Sainsbury's Avocado Ripe & Ready XL Loose 300g ",
        "size" : "38.7kb",
        ...

## License
This appliation is published with a GNU GENERAL PUBLIC LICENSE. See the LICENSE file for more details.
