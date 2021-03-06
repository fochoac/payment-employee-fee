# payment-employee-fee

## Description
Project for calculate the payment fees for the employees.

## Problem
The company ACME offers their employees the flexibility to work the hours they want. They will pay for the hours worked based on the day of the week and time of day.

## Solution Proposed
The solution was analized considerated three principal elements:

| WorkDay | Description |
| ------ | ------ |
| ExtraordinaryWorkDay | It contemplates the working hours that are carried out during the period: 00:01 - 09:00 |
| NormalWorkDay | It contemplates the working hours that are carried out during the period: 09:01 - 18:00 |
| SuplementaryWorkDay | It contemplates the working hours that are carried out during the period: 18:01 - 00:00 |

From this analysis, the corresponding classes that support each type of workday were developed.

Java provides a set of tools to check dates and times, so use the components from java.time package for performs those validations. 

Adicional, I used the Strategy Pattern with Template Pattern for performing operations to get the amount to pay to the employee.

Finally, for print the final result, I used the facade pattern to expose the method for that.

All code handle clean code and unit tests.

## Prerequisites
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

Where ${parameter} is a .txt file with least five lines. For instance:
```text
RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00
RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00
RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00
RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00
RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00
```

## Architecture
The project uses a layer architecture structured by:

| Folder | Description |
| ------ | ------ |
| enumeration | There are the classes for handle complex constants |
| exception | Handle personalized exceptions |
| model | Handle plain java objects |
| service | Handle the bussiness logic |
| main | Main class |

## Methodology
For the build de project I used Test Driven Domain with Clean Code.

## GitHub Actions
There is a action for build de project if you want to use for.

 

