# Number Conversion API

## Table of Contents

- [Purpose](#purpose)
- [Usage](#usage)
- [Swagger](#swagger)
- [Getting started as a developer](#getting-started)
- [Architecture](#architecture)
- [Code Coverage](#code-coverage)
- [Integration Testing](#integration-testing)
- [Continuous Integration](#continuous-integration)
- [Continuous Deployment](#continuous-deployment)
- [Deployment](#deployment)
- [Performance Testing with JMeter](#performance-testing-with-jmeter)
- [Logs](#logs)
- [Alerting](#alerting)
- [Metrics](#metrics)



## Purpose
This endpoint will convert any number given to it into the english words that describe that number. For example the request below should return:

### Example request 
GET /num_to_english
```json
{
    "number": "12345678" 
}
```
### Example response

```json
{
    "status": "ok",
    "num_in_english": "twelve million three hundred forty five thousand six hundred seventy eight"
}
```


Status is reserved for messaging back if the process succeeded or failed.

Any characters other than numbers will result in an error.  Also any string larger than 66 digits will exceed the limit of the conversion engine.  This limitation is based on the wikipedia article [large number name](https://en.wikipedia.org/wiki/Names_of_large_numbers) 

## Usage

Examples in postman and curl of request and responses.

### Postman
Success example
![postman success](imgs/postman-success.png?raw=true "Title")

Error example
![postman error](imgs/postman-error.png?raw=true "Title")

### cURL

Success error example:
Request
```text
curl -X GET \
  'http://localhost:8080/num_to_english?number=4500' \
  -H 'Accept: application/*' \
  -H 'Content-Type: text/*' \
  -H 'Postman-Token: 658de6c1-f05a-4d79-9923-61a8db5fb398' \
  -H 'cache-control: no-cache'
```

Response
```json
{
    "status": "ok",
    "num_in_english": "four thousand five hundred"
}
```

Decimal error example
Request
```text
curl -X GET \
  'http://localhost:8080/num_to_english?number=45.00' \
  -H 'Accept: application/*' \
  -H 'Content-Type: text/*' \
  -H 'Postman-Token: cd404a98-0464-4545-8c16-4535a5209e7b' \
  -H 'cache-control: no-cache'
```

Response
```json
{
    "status": "Number parameter failed validation Decimal not allowed",
    "num_in_english": ""
}
```
Negative error example:
Request
```text
curl -X GET \
  'http://localhost:8080/num_to_english?number=-1' \
  -H 'Accept: application/*' \
  -H 'Content-Type: text/*' \
  -H 'Postman-Token: 9a9b085e-8bb2-4cb5-9675-0379f64c6ad7' \
  -H 'cache-control: no-cache'

```

Response
```json
{
    "status": "Number parameter failed validation",
    "num_in_english": ""
}
```
Fraction error example:
Request
```text
curl -X GET \
  'http://localhost:8080/num_to_english?number=1/5' \
  -H 'Accept: application/*' \
  -H 'Content-Type: text/*' \
  -H 'Postman-Token: b4ac620b-4143-4e63-a110-52a8aadce74a' \
  -H 'cache-control: no-cache'
```
Response
```json
{
    "status": "Number parameter failed validation Fraction not allowed",
    "num_in_english": ""
}
```

Currency error example:
Request
```text
curl -X GET \
  'http://localhost:8080/num_to_english?number=$4500' \
  -H 'Accept: application/*' \
  -H 'Content-Type: text/*' \
  -H 'Postman-Token: c9b2658f-9169-4364-9789-7a6bc97565c8' \
  -H 'cache-control: no-cache'
```

Response

```json
{
    "status": "Number parameter failed validation Currency not allowed",
    "num_in_english": ""
}
```

## Swagger

The Swagger api documentation can be used for developers to better understand the api without digging into the code.
Swagger documentation available at `http://localhost:8080/swagger-ui.html#`

![Swagger UI Image 1](imgs/swagger-1.png?raw=true "Title")
![Swagger UI Image 2](imgs/swagger-2.png?raw=true "Title")



## Getting Started 
Instructions to get started for developing features or bug fixes for this api.

### Tools
1. Java 8
3. Maven
4. Docker
5. Postman manual endpoint tests
6. python3 for integration tests

### Starting from scratch
1. git clone repo
2. cd repo
3. mvn clean install
4. mvn spring-boot:run (reachable localhost:8080/num_to_english)

To perform docker steps:
### Docker
Instructions on how to run with docker.

1. docker build -t number-conversion-api .
2. docker run -p 8080:8080 number-conversion-api

We can also run with our Grafana and Prometheus installation by running

1. mvn clean package
2. mvn dockerfile:build
3. docker-compose up

Note: 
1. To log into a new local Grafana instance user: admin and pass: admin
2. Also we will need to configure the metrics in Grafana to produce our dashboards. Or in step 3 we can load the json file to generate a dashboard.
3. Please find this json dashboard and load it once you have Grafana running: `metrics-dashboard/grafana-dashboards/duration-of-each-conversion.json`

## Architecture
Proposed architecture solution.  I decided to got with ECR -> ECS fronted with ELB and API gateway.  I could also see arguments to have a lambda architecture for this application.

![AWS Architecture](imgs/AWS_Architecture_Number_Conversion_API.png?raw=true "Title")

## Code Coverage
We are using the jacoco code coverage tool.  The report is generated locally during `mvn clean install` and the index.html file is available here `target/site/jacoco/index.html`.
There is a code coverage requirement currently set at 80% which if not met will fail the build.


## Integration Testing
Integration tests are located in `integration-test/numberConversionApiTest.py`
To execute the integration tests run `python3 numberConversionApiTest.py`

## Performance Testing with JMeter
I ran some naive local performance testing with jmeter. See the dashboard below.
![perf test dashboard](imgs/perf-test-dashboard.png?raw=true "Title")

![perf test errors](imgs/perf-test-errors.png?raw=true "Title")

The files associated to perf testing are located in the perf-testing folder.
Instructions to run performance test locally
1. install jmeter
2. run `jmeter -n -t perf-test.jmx -l test1.csv -e -o output/`

## Continuous Integration
This application leverages github actions workflow to run the build and integration test on pushes to the repository.
The github actions yml file can be found in `.github/workflows/workflow.yml`.  It also leverages our docker file, mvn clean install, and integration tests during this continuous integration.
Please check the actions tab to see previous runs.

## Continuous Deployment
We could leverage a number of platforms to perform our deployments.  Ideally we would have a flow that deploys our container to ECR -> ECS in dev then qa and finally production.

At each level dev/qa/production we would perform an automated regression test which upon success or failure would deploy to the next level or perform a rollback of the new code with an automated alert to the development team.
Note: I don't have this implemented.
## Deployment
Ideally we would store our docker image in ECR and deploy to ECS with a task definition file.  We would also set up the api gateway and routes to our endpoint.


## Logs

Running locally logs will print to sys out.

In AWS logs will be sent to Cloudwatch.

## Alerting
I didn't setup alerting for errors but ideally we would use the same flow to alert on errors and then trigger further team alerts to start investigation or triage of any issues.

## Metrics
A quick overview of how Micrometer, Prometheus, and Grafana work together.
1. Micrometer creates and produces the metrics which are internal to the application and jvm that the application is running in.
2. Micrometer knows how to curate the data in such a way that Prometheus can understand it for scraping purposes.
3. Prometheus scrapes the `/actuator/prometheus` endpoint provided out of the box by Spring-Boot and gathers the data.
4. Prometheus also can be used to generate metric elements like the below example to be used in the Grafana dashboard.
```text
number_conversion_duration_seconds_sum{
application="number-conversion-api",build_artifact="number-conversion-api",
build_group="io.matt.number",build_version="0.0.1-SNAPSHOT",eventType="Conversion",instance="number-conversion:8080",
job="number-conversion-api",result="one hundred fifty four thousand six hundred seventy eight"
}
```
5. Grafana is used as a more user friendly dashboard which leverages the metric elements produced by Prometheus and also uses Prometheus as a data source for metric data.
### Micrometer

To display the exposed endpoints from Spring Boot actuators run `http://localhost:8080/actuator/prometheus`

Micrometer and Spring Boot gives us alot of metrics out of the box which are visible at the above endpoint.  We have added a custom metric to get the total duration of time for each request and below we show that data.

### Prometheus Dashboard

Prometheus metrics produced by dashboard:

![prometheus metrics](imgs/example-prometheus-metric.png?raw=true "Title")

### Grafana Dashboard

![grafana image 1](imgs/grafana-duration-by-each-conversion.png?raw=true "Title")

![grafana image 1](imgs/total-count-of-conversions-by-conversion.png?raw=true "Title")


