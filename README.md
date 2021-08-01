Betfair Exchange API Bot
========================

[![Build Status](https://app.travis-ci.com/AJ8GH/spring-boot-api-bot.svg?branch=main)](https://app.travis-ci.com/AJ8GH/spring-boot-api-bot)

Web app designed to interact with Betfair's Developer API. Built using Java 11 and Spring Boot.

## Getting started

Clone this repository
```shell
git@github.com:AJ8GH/spring-boot-api-bot.git
```

Create a properties file called `application.properties` under the resources directory 
`betfair-api-bot-1/com/aj/main/resources/application.properties`.

Define your app key

```properties
# application.properties
APP_KEY=yourAppKey
```

Navigate to the project root and run a clean and install using Maven:

```shell
cd api-bot-project
mvn clean install
```

## Running tests

Tests can be run from the root of the project using:

```shell
mvn test
```

## Running the app

Start the server with:

```shell
mvn spring-boot:run
```

Then go to local host in your browser, the default port will be 8080:

http://localhost:8080/

If needed a different port can be specified in `application.properties`:

```properties
server.port=yourSpecifiedPort
```

## Purpose

The goal of this project was to create a functional bot which can run operations through the Betfair Exchange Developer API. Operations include placing and cancelling bets, viewing events and markets, and retrieving best prices for individual markets.  

The wider aim was to learn about Java, Spring and the betfair domain whilst applying OOP principles.

## Design

### Beans

- `ApiClient` - responsible for making calls to the API
- `RequestBodyBuilder` - dependency of ApiClient, creates a String of the correct request body for each api call
- `UrlBuilder`  dependency of ApiClient, creates an HttpUrl Url with the correct endpoint for each api call
    
### Controllers

Each controller handles mapping requests for their corresponding domain:
- `EventTypeController`
- `BetController`
- `SessionController`

### Models

- `EventType` - represents an individual sport
- `Bet` - a user's bet placed on the exchange
- `Session` - encapsulates session information, including token and app key

### CI

Travis CI runs a build on each push, sending test coverage stats to codecov on a successful build.

## Dependencies

### Build Tool
- Maven

### JDK
- 11.0.11

### Artifacts
- junit-jupiter
- mockwebserver
- okhttp
- mockito-core
- mockito-junit-jupiter
- jackson-databind
- jackson-core
