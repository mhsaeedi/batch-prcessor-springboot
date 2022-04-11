#!/bin/bash

./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=cloud-indexation-agent:latest
docker-compose up
