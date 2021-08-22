Betfair Exchange API Bot
========================

[![Build Status](https://app.travis-ci.com/AJ8GH/spring-boot-api-bot.svg?branch=main)](https://app.travis-ci.com/AJ8GH/spring-boot-api-bot)
[![Maintainability](https://api.codeclimate.com/v1/badges/53b09461685bd87a395c/maintainability)](https://codeclimate.com/github/AJ8GH/spring-boot-api-bot/maintainability)
[![codecov](https://codecov.io/gh/AJ8GH/spring-boot-api-bot/branch/main/graph/badge.svg?token=PCEGI1SPQ2)](https://codecov.io/gh/AJ8GH/spring-boot-api-bot)

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
server.port=PORT
```

## Purpose

The goal of this project was to create a bot which can get data, place and cancel bets through the Betfair Exchange API, whilst learning Java, Spring and the Betfair domain.

## Design

The app is built with an MVC architecture using Spring Boot and Spring MVC. 

### Models

The Betfair domain is modelled into Java objects and Enums in the domain package. 

### Views

Thymeleaf templates are used in the view layer to display data through the UI.

### Controllers

Each controller handles mapping requests for their corresponding domain:
- `SessionController` - maps login and index routes
- `EventTypeController` - maps routes for listing events and event types
- `BetController` - maps routes for placing, listing and cancelling bets
- `MarketController` - maps routes for listing market books and market catalogues, as well as ESA market subscriptions
- `AppErrorController` - default mapping for exceptions.

### API Client

The `ApiClient` class interacts with the API and returns a json response string.

It relies on 2 dependencies, in the `UrlBuilder` and the `requestBodyFactory` classes.
The url builder constructs the correct endpoint for each operation. The request body factory, builds a `RequestBody` object and returns it as a serialised JSON payload. 

### Enrichment

The `Enricher` is used to enrich objects with additional fields which they don't contain by default. It takes an object to enrich and uses a `MarketCatalogue` to populate the event type name, event name, market name and runner name.

### Serialisation & Deserialisation

Jackson ObjectMapper is used in the `JsonDeserialiser` class, to provide an interface for custom deserialisation.

### ESA Client

The `EsaClient` provides an interface to connect via websocket to the Exchange Stream API. It handles connection, authentication and market subscription operations. It can be extended to perform additional operations if needed.

### Persistence

JPA repositories are used to persist data, along with a Cache, which is used to cache the market change data from the esa stream.

### CI

Travis CI runs a build on each push, sending test coverage stats to codecov on a successful build.
CodeCov and Jacoco track test coverage.

### Testing

Classes and methods are unit tested with JUnit, dependencies are mocked using Mockito.

The controller layer is tested using Springs MockMvc library, allowing integration tests of the web routing, whilst injecting mock beans to mock dependencies.

API calls are mocked using OkHttp's MockWebServer library.

## Dependencies

### Build Tool
- Maven

### JDK
- 11.0.11

### Artifacts
- Full list of dependencies can be found in the `pom.xml` file in the root directory
