:: set docker settings
SET DOCKER_USER=oislen
SET DOCKER_REPO=czechtutor
SET DOCKER_TAG=latest
SET DOCKER_IMAGE=%DOCKER_USER%/%DOCKER_REPO%:%DOCKER_TAG%
SET DOCKER_CONTAINER_NAME=ct
SET UBUNTU_DIR=/home/ubuntu

:: remove existing docker containers and images
docker image rm -f %DOCKER_IMAGE%

:: build docker image
call docker build --no-cache -t %DOCKER_IMAGE% .

:: run docker container
call docker run --name %DOCKER_CONTAINER_NAME% --publish 8080:8080 --memory 7GB --rm %DOCKER_IMAGE%
::call docker run -it --memory 7GB --rm %DOCKER_IMAGE%

:: useful docker commands
:: docker images
:: docker ps -a
:: docker exec -it {container_hash} /bin/bash
:: docker stop {container_hash}
:: docker start -ai {container_hash}
:: docker rm {container_hash}
:: docker image rm {docker_image}
:: docker push {docker_image}