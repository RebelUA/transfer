## Description

RESTful money transfer service

## Technologies

**Netty**  - web server with custom router <br>
**Hazelcast** - in memory data store <br>
**Typesafe** - configuration <br>
**Lombok** - pills from checked exceptions headache <br>
**org.JSON** - JSON implementation <br>
**Apache Http Client** - client for integration testing <br>
**Guava** - striped lock on key <br>
**JUnit** - for junit staff

## Configuration

Application configuration is stored in `application.json` file

### Available params

- `rest-port`: Int - port for web server to start on
- `executor-threads`: Int - number of threads for concurrent request handling

## Run

**Prerequisites**
- Java 12 (Only Oracle JDK has been tested)

To start application *gradle* task `run` should be executed. Gradle wrapper `gradlew run` could be used if no supported gradle version is installed

## Integration Tests Run

**Prerequisites**
- Java 12 (Only Oracle JDK has been tested)

To start application's integration tests *gradle* task `integrationTest` should be executed. Gradle wrapper `gradlew integrationTest` could be used if no supported gradle version is installed

## API

- **POST `/account/create`** <br>
**Response:** {"accountId": "`created-account-id`"} <br>
**Description:** creates new account and returns its id <br><br>
- **POST `/lotteryWinner`** <br>
**Params:** `account`: *String*, `amount`: *Long* <br>
**Response:** {"message", "Wow, we have a winner here!"} <br> 
**Description:** increases balance of account with `account` id by `amount`, `amount` should be > 0 <br><br>
- **GET `/account/balance`** <br>
**Params:** `account`: *String* <br>
**Response:** {"balance": `balance`} <br>
**Description:** returns `balance` of account with `account` id <br><br>
- **POST `/transfer`** <br>
**Params:** `debit`: *String*, `credit`: *String*, `amount`: *Long* <br>
**Response:** {"message": "Transfer successful"} <br>
**Description:** transfers `amount` of money from account `debit` to account `credit`, `amount` should be > 0, `debit` should not be equal `credit`<br>

## Known Issues

To avoid hazelcast performance issues due to partial incompatibility with Java Jigsaw modular system JVM params should be added: `--add-modules java.se --add-exports java.base/jdk.internal.ref=ALL-UNNAMED  --add-opens java.base/java.lang=ALL-UNNAMED  --add-opens java.base/java.nio=ALL-UNNAMED  --add-opens java.base/sun.nio.ch=ALL-UNNAMED  --add-opens java.management/sun.management=ALL-UNNAMED  --add-opens jdk.management/com.ibm.lang.management.internal=ALL-UNNAMED  --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED` 