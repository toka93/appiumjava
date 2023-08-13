# How to setup the project?

- Install JDK 20: 
- Install Maven:
- Install Eclipse IDE:
https://www.eclipse.org/downloads/
- Open Eclipse and open Help List and choose Install New Software , Search for TestNG and Install it.
- Open file List and choose Open project from file system and choose project directory 



#  Configuration :
Appium Configuration & Device Configuration inside test/resources 

Log4j Configuration inside main/resources
 
   

# Test Data: 
 saved in json file inside Data Folder.



# Steps:
- update device properties and make sure it matches the emulator you're using.
- start emulator.
- start appium server.
- make sure that emulator has app store and install app. 
- Open CMD
- CD to the directory contains the project
- mvn test (android is set as default value) we can pass ios if we want run on ios.
- run from Eclipse IDE from class file or from suite file.
  

# After Every Run : 
- Report and Log  for every test will be generated.
- Reports in Reports folder and logs in logs folder.

# Test Design:

 2 test cases covers [login scenario and choosing site , check settings page and logout]

# Automation Strategy

project to test mobile apps using appium with java 

- The project is designed with page object and factory design patterns to have the ability to extend for the ios app and with applying oop.
- Factory design pattern was applied by creating a driver interface and 2 classes to implement it one for android and one for ios. 
- Page object was applied by creating sperate class for each page in the app holds locators and actions. 
- Data is saved in json file.
- Extent Report library is used for reporting.
- Log4j is used to generate logs. 
- Testng used for handling running, assertions and handle tests life cycle by using annotations.


