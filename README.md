# crwd-app
## Overview
Crwd app is a test python app.
     
It starts on port 5001 and exposes 2 endpoints - / and /hello.

## Validation
I have added a simple test to validate the app is working as expected.
Test can be found in `tests` directory. It uses pytest and requests library to validate the app.
Tests can be also run locally with the command: ./test.sh

## Dependencies
I have added a requirements.txt file to install the dependencies.

## Pipeline
It contains a Jenkinsfile which is used to trigger the build and deployment of the app to a local k8s cluster.
Jenkinsfile can be found in the `jenkins` directory.

## Important notes
I have updated the 5000 port to 5001 (on my laptop i had the 5000 port already in use).
I have updated the host ip of the server to 0.0.0.0 to be able to be accessible from outside the container.

## Docker
I have added a Dockerfile to build the image of the app.

## Versioning
I have added a version file to keep track of the app version. 
This will be reflected in the docker build tags as well.


