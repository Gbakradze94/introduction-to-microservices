FROM  openjdk:17-alpine
MAINTAINER gbakradze
COPY build/libs/microservices-architecture-overview-0.0.1-SNAPSHOT.jar microservices-architecture-overview-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/microservices-architecture-overview-0.0.1-SNAPSHOT.jar"]