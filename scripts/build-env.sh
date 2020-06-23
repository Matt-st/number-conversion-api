#!/bin/bash
docker build -t  number-conversion-api ../.
docker run -d --name number-conversion-integration-test -p 8080:8080 number-conversion-api


# give time for container to start
sleep 10
# run integration tests on container
cd ../integration-test

python3 numberConversionApiTest.py

