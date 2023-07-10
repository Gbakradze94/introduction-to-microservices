FROM  openjdk:17
MAINTAINER gbakradze
COPY build/libs/resource-service-plain.jar resource-service-plain.jar
ENTRYPOINT ["java","-jar","/resource-service-plain.jar"]