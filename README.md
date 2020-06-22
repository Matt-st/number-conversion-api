# Number Conversion API

## Purpose
This endpoint will convert any number given to it into the english words that describe that number. For example the above request should return:


### Example response
{
    “status”: “ok”,
    “num_in_english”: “twelve million three hundred forty five thousand six hundred seventy eight” 
}

Status is reserved for messaging back if the process succeeded or failed.


## Getting Started 
Instructions to get started for developing features or bug fixes for this api.

### Tools
1. Java 8
3. Maven
4. Docker
5. Postman manual endpoint tests

### Starting from scratch
1. git clone repo
2. cd repo
3. mvn clean install
4. mvn spring-boot:run (reachable localhost:8080/num_to_english)

## Swagger - api documentation
Swagger documentation available at 
![Swagger UI Image 1](img/swagger-1.png?raw=true "Title")
![Swagger UI Image 2](img/swagger-2.png?raw=true "Title")
## Docker
Instructions on how to run with docker.

1. docker build -t number-conversion-api .
2. docker run -p 8080:8080 number-conversion-api

## Logs

## Alerting

### Micrometer
Adding counter and timer and gauge for cache
### Prometheus Dashboard

### Grafana Dashboard

## Postman
![postman success](imgs/postman-success.png?raw=true "Title")
![postman error](imgs/postman-error.png?raw=true "Title")
Add error examples

## Code Coverage
We are using the jacoco code coverage tool.  The report is generated locally during `mvn clean install` and the index.html file is available here `target/site/jacoco/index.html`.
There is a code coverage requirement currently set at 80% which if not met will fail the build.
TODO:Add image for check goal
## Integration Testing
Integration tests are located in `integration-test/numberConversionApiTest.py`
To execute the integration tests run `python3 numberConversionApiTest.py`

## CI/CD
This application leverages github actions workflow to run the build and integration test on PR commits.

Please check the actions tab to see previous runs.

## Deployment

### Cloud Formation Template