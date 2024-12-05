# Czech Tutor

## Overview

A simple single page website for practising Czech phrases and vocabulary.

## Running the Application

### Docker Image

The latests image can be found on dockerhub here:
* https://hub.docker.com/repository/docker/oislen/czechtutor/general

The latest image can be pull using:

```
docker pull oislen/czechtutor:latest
```

The webapp can be executed using:

```
docker run --name ct --publish 8080:8080 --memory 7GB --rm oislen/czechtutor:latest
```

## Data Model

Czech to English phrases and vocabulary sourced from:

* https://www.manythings.org/anki/
* https://apps.ankiweb.net/
* https://tatoeba.org/en

The underlying data model present used in CzechTutor is displayed below. 

![Entity Relationship Diagram](doc/ER.jpg)

For a more detailed account of each column in the dataset see the data dictionary:

* TODO