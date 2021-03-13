# EBI-Assessment


Steps to Setup
1. Clone the application

https://github.com/concy777/EBI-Assessment.git

2.Build and run the app using maven

mvn package
java -jar target/ebi-docker.jar
Alternatively, you can run the app without packaging it using -

mvn spring-boot:run
The app will start running at https://localhost:8443

Explore Rest APIs
The app defines following CRUD APIs.

GET /persons

POST /persons

GET /persons/{id}

DELETE /persons/{id}


Useful curl commands to test. It can be tested with swagger also: https://localhost:8443/swagger-ui.html




Using the Dockerfile create the Docker image. 
From the directory of Dockerfile :

docker build . -t ebi-docker

Run the Docker image (ebi-docker) created. 

docker build . -t ebi-docker


Useful Docker commands
docker images
docker container ls
docker logs <container_name>
docker container rm <container_name
docker image rm <image_name
