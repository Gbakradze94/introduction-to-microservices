To build and create docker images for all projects from parent:
gradle clean build bootBuildImage

-- To execute gradle task on specific project from parent:
gradle clean :resource-service:bootRun
gradle clean :resource-service:build :resource-service:bootBuildImage
gradle clean :song-service:build :song-service:bootBuildImage

-- To run resource-service:
docker run -p 8081:8081 docker.io/microservice/microservices-architecture-overview-resource-service:latest

-- To run from WSL2:
cd /mnt/c/users/Giorgi_Bakradze/IdeaProjects/introduction-to-microservices

-- To run with docker compose, from the parent project run the following:
docker compose up