To build and create docker images for all projects from parent:
gradle clean build bootBuildImage

-- To execute gradle task on specific project from parent:
gradle clean :resource-service:bootRun
gradle clean :resource-service:build :resource-service:bootBuildImage
gradle clean :song-service:build :song-service:bootBuildImage

-- To run resource-service:
docker run -p 8081:8081 docker.io/microservice/microservices-architecture-overview-resource-service:latest

-- To run with docker compose, navigate to the project directory:
cd resource-service or cd song-service
-- and run the following command:
docker compose up