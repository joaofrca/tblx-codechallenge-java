# TB.LX CODE CHALLENGE - JAVA

This repository provides all dependencies to run a web service that exposes a RESTful API written in Java with MongoDB.

## Installing MongoDB

MongoDB Community Edition was used in this project. Installation requirements and guide can be found [here](https://docs.mongodb.com/manual/administration/install-community/).

As the GUI for MongoDB, MongoDB Compass was used. It can downloaded for free [here](https://www.mongodb.com/try/download/compass).

## Installing Postman

Postman is a collaboration platform for API development, and can be downloaded for free [here](https://www.postman.com/downloads/). It was the tool used for the construction and testing of the API endpoints.

## Execution

#### 0. Clone repo to your local

On the folder's directory run:

```
git clone https://github.com/joaofrca/tblx-codechallenge-java.git
```

#### 1. Data Load

In order to use the mongoDB in this project, one shall manually import the data into the MongoDB.

On the folder's directory, navigate to ./dataset folder. From the zip file which can be found [here](https://codechallengestracc.blob.core.windows.net/code-challenge/dublin-dataset.zip), download the 'siri.20130130.csv' file and add into the folder, making it two csv files:

- siri.20130130.csv
- siri.20130130-small-test.csv

These files contain the dataset used. A first line shall be added into 'siri.20130130.csv', containing the following header of the model/schema:

```
timestamp,lineID,direction,journeyPatternID,timeframe,vehicleJourneyID,operator,congestion,lon,lat,delay,blockID,vehicleID,stopID,atStop
```
The file 'siri.20130130-small-test.csv' already contains the above header, as an example.

To load the data, one shall manually run, in the ./dataset path:

```
mongoimport -d <db_name> -c <collection_name> --type csv --file <file_name> --headerline
```

The variables to populate shall be:

```
db_name = busDEV
collection_name = busgps
file_name = siri.20130130.csv
```

Making the whole command:

```
mongoimport -d busDEV -c busgps --type csv --file siri.20130130.csv --headerline
```

Note: any file from the zip file can be used. If a different dataset is to be used, one shall modify the script accordingly and add the headerline to the csv file which will be loaded.

#### 2. Run the project

On the folder's directory run:

```
./mvnw spring-boot:run
```

The server will run on localhost:6039.

#### 3. Play around with the app using Postman

Use Postman to try the application. Four routes are available:

1.  Given a time frame [start-time, end-time], what is the list of running operators?

- get : [localhost:6039/operators/:starttime/:endtime](http://localhost:6039/operators/:starttime/:endtime)
  Requires keys:
   * startTime in ISO-8601 format.
   * endTime in ISO-8601 format.

2. Given a time frame [start-time, end-time] and an Operator, what is the list of vehicle IDs?

- get : [localhost:6039/vehicles/:starttime/:endtime/:operator](http://localhost:6039/vehicles/:starttime/:endtime/:operator)
  Requires keys:
  * startTime in ISO-8601 format.
  * endTime in ISO-8601 format.
  * operator ID.

3. Given a time frame [start-time, end-time] and a fleet, which vehicles are at a stop?

- get : [localhost:6039/vehiclesAtStop/:starttime/:endtime/:operator](http://localhost:6039/vehiclesAtStop/:starttime/:endtime/:operator)
  Requires keys:
  * startTime in ISO-8601 format.
  * endTime in ISO-8601 format.
  * operator ID.

4. Given a time frame [start-time, end-time] and a vehicle, return the trace of that vehicle (GPS entries, ordered by timestamp).

- get : [localhost:6039/vehicleTrace/:starttime/:endtime/:vehicleID](http://localhost:6039/vehicleTrace/:starttime/:endtime/:vehicleID)
  Requires keys:
  * startTime in ISO-8601 format.
  * endTime in ISO-8601 format.
  * vehicle ID.

All Routes require the parameters in the Endpoint to be modified accordingly. It is not required a body in the request.

Note: Do not forget to add data to the MongoDB as explained in step 2.

#### 4. Run the tests

On the folder's directory, navigate to the following path:

```
/src/test/java/com/tblx/api
```

Here you can find the Controller and Service Tests.

Note: Do not forget to add data to the MongoDB as explained in step 2.

# Examples

Take a look at the examples below, and copy the content of swagger/tblx-codechallenge-java.yml file into https://editor.swagger.io/, for swagger documentation. 

#### 1. localhost:6039/operators/:starttime/:endtime

- starttime = 2012-11-30T00:00:01
- endtime = 2012-11-30T00:10:00
  > expected result = [ "CD", "RD", "CF", "HN", "SL", "D1", "D2", "PO" ]

#### 2. localhost:4000/task2/:starttime/:endtime/:operator

- starttime = 2012-11-30T00:00:01
- endtime = 2012-11-30T00:10:00
- operator = HN
  > expected result = [ 33223, 40012, 40014, 33167, 33552, 40017, 40021, 40025, 43034, 40027, 33500, 40029, 33501, 40032, 33184, 43042, 43043, 33510, 43046, 33513, 33452, 33453, 33455, 38070, 38071, 38074 ]

#### 3. localhost:4000/task3/:starttime/:endtime/:operator

- starttime = 2012-11-30T00:00:01
- endtime = 2012-11-30T00:10:00
- operator = HN
  > expected result = [ 40032, 33223, 33513, 33453, 40014, 40021, 38070, 40025, 43034, 40027, 33500, 40029, 33501 ]

#### 4. localhost:4000/task4/:starttime/:endtime/:vehicleID

- starttime = 2012-11-30T00:00:01
- endtime = 2012-11-30T00:10:00
- vehicleID = 33298
  > expected result = [
                {
                    "timestamp": "2012-11-30",
                    "lon": "-6.378427,
                    "lat": "53.319969
                },
                {
                    "timestamp": "2012-11-30",
                    "lon": "-6.378461",
                    "lat": "53.323204"
                },
                ...
            ]

# The Challenge formulation:

The goal of this challenge is to build a web service that exposes Vehicle (Bus), Fleet (Operator), and Activity (Stop) data, for a given time frame.

The service exposes a RESTful API to answer the following questions:

1.  Given a time frame [start-time, end-time], what is the list of running operators?

2.  Given a time frame [start-time, end-time] and an Operator, what is the list of vehicle IDs?

3.  Given a time frame [start-time, end-time] and a fleet, which vehicles are at a stop?

4.  Given a time frame [start-time, end-time] and a vehicle, return the trace of that vehicle (GPS entries, ordered by timestamp).

Before implementing this exercise, you need to:

§ Choose a database management system that is more appropriate for building this web service (we value NoSQL databases, but a relational database may be also fine);

§ Choose a programming language that is type-safe and you are familiar with;

§ Choose a web toolkit that is able to handle high throughput in terms of number of requests (we are less worried about latency, but that's not to be neglected as well).

What to deliver:

· A git code repo, public or private, that can be accessed by our team, with the following:

· Instructions on how to install and/or access to the database;

· A data loader script;

· The code of the web service;

· Instructions on how to launch the HTTP service;

· Documentation and/or examples on how to use the API.

Dataset to use:

At this moment there are some issues with this dataset from Dublin but we managed to extract the files and you should be able yo find some of the data in this link:

Name: Dublin Bus GPS sample data from Dublin City Council

      URL:  https://codechallengestracc.blob.core.windows.net/code-challenge/dublin-dataset.zip

      URL: https://data.gov.ie/dataset/dublin-bus-gps-sample-data-from-dublin-city-council-insight-project

Download one extract, and from that extract, use 1 example CSV as input

# TODOs

- Improve tests:
  - Add more unhappy flows / exceptions on tests.
  - Test for VehicleTraceService.getVehicleTrace(...) still not working.
  - Controller tests should test the endpoint and not the method name itself - check commented lines on OperatorsControllerTest. To be implemented correctly.
- Improve Swagger documentation.
- Add tool to standardize the code formatting for all project.
- Substitute starttime and endtime parameters by query strings.
- Add MongoDB as a docker image, to avoid users having to install MongoDB on their loval environments.
- Improve date conversions.

# Made in Lisbon with ♡
