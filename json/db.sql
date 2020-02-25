CREATE DATABASE TotalTripper;
DROP TABLE IF EXISTS Flights;
CREATE TABLE Flights (
    FlightId INT,
    FlightNo VARCHAR(10),
    "From" VARCHAR(100),
    "To" VARCHAR(100),
    "dateTime" VARCHAR,
    "Status" VARCHAR(255)
);