# demo-orangehrmlive-automation-TestNG

## Overview
This project automates various test scenarios for the OrangeHRM demo website using Selenium WebDriver, TestNG, and Gradle. It includes test cases for functionalities such as admin login, employee creation, and new employee login.

## Prerequisites

To run this project, you need to have the following installed on your machine:

* Java Development Kit (JDK) (version 8 or higher)
  - Download JDK
  - Ensure JAVA_HOME is set and included in the system PATH.
* Install Gradle that matches with the JDK version.
* Selenium: Set up the Selenium WebDriver for automation.
* TestNG: For running and managing the test scripts.

## Project Structure

OrangeHRM-Demo-Automation/
│                
├── src/                  (Source folder)                              
│   ├── main/             (Main source folder)                
│   └── test/             (Test source folder)                  
│       ├── java/         (Test classes)            
│       ├── resources/    (TestNG suite XMLs, test data (JSON, etc.))     
├── build.gradle          (Project configuration file for Gradle)      
├── settings.gradle       (Project settings)      
└── README.md             (This file)             


## Setup Instructions

* Clone the repository:
  - git clone ```https://github.com/AmenaIslamRimi/demo-orangehrmlive-automation-TestNG.git```
* Navigate to the project directory:
  - cd OrangeHRM-Demo-Automation
* Install dependencies using Gradle:
  - selenium java
  - testNG
  - java faker
  - json simple
  - allure testNG

## How to run

* Run All Tests(full suite):
  - gradle clean test
* Run Specific TestNG Suite:
  - gradle test --tests testRunner.LoginTestRunner
  - gradle test --tests testRunner.PIMTestRunner
  - gradle test --tests testRunner.UserLoginTestRunner

## OrangeHRM Automation Test Cases 
https://docs.google.com/spreadsheets/d/1x-_2zRA54dTyE6K-nIXBAcU9ivbz8UaM-Xu9doIirOc/edit?gid=0#gid=0

## OrangeHRM Test Summary
![automation test summary](https://i.postimg.cc/c4nn2sRg/Orange-HRM-Automation-Test-Summary.png)

## OrangeHRM Allure Results
![automation overview result](https://i.postimg.cc/VkPCc3g7/orange-HRM-automation-allure-overview.png)
![automation behavior result](https://i.postimg.cc/BvMLHt0y/orange-HRM-automation-allure-behavior.png)

## OrangeHRM Test Record

https://github.com/user-attachments/assets/edcce576-b28e-4ba6-970b-9ac379d49a07

