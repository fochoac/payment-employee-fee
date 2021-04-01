# payment-employee-fee

## Description
Project for calculate the payment fees for the employees.

##Problem
The company ACME offers their employees the flexibility to work the hours they want. They will pay for the hours worked based on the day of the week and time of day.

## Solution Proposed
Java provides a set of tools to check dates and times, so use the components from java.time package for performs those validations. 

Adicional, I used the Strategy Pattern with Template Pattern for performing operations to get the amount to pay to the employee.

Finally, for print the final result, I used the service layer por expose the method for that.

All code handle clean code and unit tests.

##Prerequisites
The project use:
- Java 8 or higher
- Maven 3

## Build
Execute in a terminal:

```sh
mvn clean test
mvn clean install
```

## Run
Execute in a terminal:

```sh
java -jar payment-employee-fee-1.0.0-SNAPSHOT.jar ${parameter}
```

Where ${parameter} is a data frame. For instance:
```text
RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00
```

## Architecture
The project use a layer architecture structured by:

| Folder | Description |
| ------ | ------ |
| enumeration | There are the classes for handle complex constants |
| exception | Handle personalized exceptions |
| model | Handle plain java objects |
| service | Handle the bussiness logic |
| main | Handle the bussiness logic |

## Methodology
For the build de project I used Test Driven Domain with Clean Code.


 

