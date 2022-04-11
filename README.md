# User Session Service


## Requirements
- Java 11
- Kotlin 1.5
- Maven
- Redis

## How do I get set up?
* Install JDK 11
* Library dependencies could be obtained by running 'mvn dependency:resolve' command.

### Summary of set up


### Configuration
There are 4 application configuration files. application.yaml is the common one for all profiles. Besides, there are 3 more configuration files for 
  development, staging and production environments.

Application management endpoints are provided for "health" information. It can be accessed via /system/health path. Readiness and liveness
probes can also be found under health.

Config parameters can be found in below table.

| ENV VAR | SPRING BOOT CONF | DESCRIPTION | DEFAULT VALUE| EXAMPLE |
| ------- | ---------------- | ----------- |:------------:|:-------:|
|APP_SERVER_PORT|server.port|Application server port|8080|8080|
|MANAGE_SERVER_PORT|management.server.port|Application management server port|8081|8081|

### Dependencies

* Does the project have any dependencies?

### Database configuration

* Which databases are being used? How should they be configured for the software to work?

### How to run tests

* How to test the application to make sure that everything is working fine?

### Deployment instructions

Application can be run standalone using 'mvn spring-boot:run' command. Applications health status can be checked using '/health' endpoint from 
management API like 'localhost:8081/system/health'.
  
* How to deploy the application to production?

## Contribution guidelines

* Writing tests (do we follow any convention for the naming?)
* Code review 
* Other guidelines

### Who do I talk to? ###

- Abrahale Tewelde (abrahale.tewelde@visual-meta.com)
- Johannes Günter (johannes.guenther.external@visual-meta.com)
- Mario Bislick (mario.bislick.external@visual-meta.com)
- Orkun Akile (orkun.akile@visual-meta.com)
