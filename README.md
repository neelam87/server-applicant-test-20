# backend applicant test

## Task Description
You should be able to start the example application by executing com.serverApplicantTestApplication, which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoints.

The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring Boot
* Database H2 (In-Memory)
* Maven


You should be aware of the following conventions while you are working on this exercise:

 * All new entities should have an ID with type of Long and a date_created with type of ZonedDateTime.
 * The architecture of the web service is built with the following components:
 	* DataTransferObjects: Objects which are used for outside communication via the API
    * Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
    * Service: Implements the business logic and handles the access to the DataAccessObjects.
    * DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
    * DomainObjects: Functional Objects which might be persisted in the database.
 * TestDrivenDevelopment is a good choice, but it's up to you how you are testing your code.
 * Feel free to use Java as well as Kotlin
 * We do provide code formatter for IntelliJ IDEA and Eclipse in the etc folder

You should commit into a local git repository and include the git repository (.git/) in the upload.

_NOTE: Please DO NOT publish the project, e.g. by uploading it to GitHub or the like!_

---


## Task 1
 * Write a new Controller for maintaining cars (CRUD).
   * Decide on your own how the methods should look like.
   * Entity Car: Should have at least the following characteristics: license_plate, seat_count, convertible, rating, engine_type (electric, gas, ...)
   * Entity Manufacturer: Decide on your own if you will use a new table or just a string column in the car table.
 * Extend the DriverController to enable drivers to select a car they are driving with.
 * Extend the DriverController to enable drivers to deselect a car.
 * Extend the DriverDo to map the selected car to the driver.
 * Add example data to resources/data.sql
 
 
 Step 1: Enable drivers to select a car they are driving with
 1.1)  Create the driver if not exists
 
 http://localhost:8080/v1/drivers?access_token=bfe83d55-4c62-4878-9ac6-286ee76dfea5
 
  Request:  
  POST METHOD:
  
  {
  "coordinate": {
    "latitude": 0,
    "longitude": 0
  },
  "id": 30,
  "onlineStatus": "ONLINE",
  "password": "driver030",
  "username": "driver_300"
}

Response: 
{
    "id": 7,
    "username": "driver_300",
    "password": "driver030"
}

1.2: Driver select a car

GET METHOD:
http://localhost:8080/v1/drivers/selectCar?carModelName=Honda&driverId=7&access_token=9e989108-e98e-4a06-af45-899f9d9c27e4

1.3: Driver deselect the car

DELETE METHOD: 
http://localhost:8080/v1/drivers/deSelectCar?driverId=7&access_token=542db5a7-540e-4849-8bc0-384c7c2ffa8d

---


## Task 2
First come first serve: A car can be selected by exactly one ONLINE Driver. If a second driver tries to select a already used car you should throw a CarAlreadyInUseException.

Step 1: If you dont know the driver details:
1.1) Create the driver using below endpoint 

http://localhost:8080/v1/drivers?access_token=bfe83d55-4c62-4878-9ac6-286ee76dfea5

GET METHOD:

Request body: 
{
  "coordinate": {
    "latitude": 0,
    "longitude": 0
  },
  "id": 15,
  "onlineStatus": "ONLINE",
  "password": "driver_n01",
  "username": "driver_001"
}

Response:

{
    "id": 2,
    "username": "driver_001",
    "password": "driver_n01"
}

1.2) If you have the driver details, use its driver id
1.3) 
If car is already occupied by another driver 

http://localhost:8080/v1/drivers/selectCar?carModelName=Honda&driverId=2&access_token=57658ab3-871c-40c8-a7f3-9221216d9429

Response:
{
    "timestamp": "2019-02-21T19:45:13.238+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Car is already been used by another driver.",
    "path": "/v1/drivers/selectCar"
}

---


## Task 3
Imagine a driver management frontend that is used internally by mytaxi employees to create and edit driver related data. For a new search functionality, we need an endpoint to search for drivers. It should be possible to search for drivers by their attributes (username, online_status) as well as car characteristics (license plate, rating, etc).

* implement a new endpoint for searching or extend an existing one
* driver/car attributes as input parameters
* return list of drivers

---

Endpoint to search driver with driver details and car details: 

GET method:  http://localhost:8080/v1/drivers 



## Task 4 (optional)
This task is _voluntarily_, if you can't get enough of hacking tech challenges, implement security.
Secure the API so that authentication is needed to access it. The details are up to you.

Please include instructions how to authenticate/login, so that we can test the endpoints you implemented!


I used spring security with OAuth 2.0 concept:

1) When you will hit this url 

http://localhost:8080/v1/drivers/selectCar?driverId=5

Response: 

it will throw the error:

{
    "error": "unauthorized",
    "error_description": "An Authentication object was not found in the SecurityContext"
}

Step 2: Generate the access token and refresh token by executing below url:

http://localhost:8080/oauth/token?grant_type=password&username=testNeel&password=123456

Response: 

{
    "access_token": "814a9ded-eb94-4ce0-ad52-59f76efdc54a",
    "token_type": "bearer",
    "refresh_token": "87978e22-3a76-4d79-82f1-274722880665",
    "expires_in": 119,
    "scope": "read write trust"
}

Step 3: 

Once you get the access token then execute the above url:

http://localhost:8080/v1/drivers/8/?access_token=97361e8a-b426-4c19-bd34-3a963543798d

Response: 

{
    "id": 8,
    "username": "driver08",
    "password": "driver08pw",
    "coordinate": {
        "latitude": 55.954,
        "longitude": 9.5
    }
}

---


Good luck!
❤️ mytaxi



_NOTE: Please make sure to not submit any personal data with your tests result. Personal data is for example your name, your birth date, email address etc._
