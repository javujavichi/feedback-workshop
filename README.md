# feedback-workshop
Workshop for SoCraTes with arch unit

## Set up
To work in this project you'll need **Java SDK 11** and **gradle** to configure it.
You can use https://sdkman.io/ to configure java sdk and gradle

install gradle
```bash
sdk install gradle 6.4
```

## Running the application locally
Start the application locally

```bash
./gradlew bootrun
```

# Available endpoints 
```
POST /feedback: Submit feedback
GET /feedback: Get all the provided feedback
GET /feedback/{id}: Get feedback by ID
GET /feedback?givenBy={givenBy}: Get feedback by givenBy parameter
```