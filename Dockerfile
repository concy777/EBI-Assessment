FROM openjdk:8
EXPOSE 8080
ADD target/ebi-docker.jar ebi-docker.jar
ENTRYPOINT ["java","-jar","/ebi-docker.jar"]