# feedback-workshop
Workshop for SoCraTes Conference to play with arch unit and enforce your architecture using Java

## Instructions

This project has a few branches to play and work with arch unit, the main idea is to refactor each *chaos branch* using the available architecture tests. You can always add more tests or refactor the application in order to pass a test.

## Set up your environment
To work in this project you'll need **Java SDK 11** and **gradle** to configure it.
You can use https://sdkman.io/ to configure java sdk and gradle

### install gradle
```bash
sdk install gradle 6.4
```

### For VSCode development
#### Extensions to work with Java (optional)

This are the extentions that I have in my VSCode

- Debugger for Java
- Extension Pack for Java
- Gradle for Java
- Language Support for Java(TM) by Red Hat
- Project Manager for Java
- Test Runner for Java

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
GET /feedback/givenBy/{givenBy}: Get feedback by givenBy parameter
```

### Request body
```json
{
  "id": {{$randomInt}},
  "givenBy": "Rose",
  "feedback": "Should study more architecture"
}
```