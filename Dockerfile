# Multi-stage Dockerfile: build with Gradle, run with a small JRE
FROM gradle:6.4.1-jdk11 AS builder
WORKDIR /home/gradle/src

# Copy the project and use the Gradle wrapper to build the jar.
COPY --chown=gradle:gradle . /home/gradle/src

# Build the executable jar (uses wrapper so no local Gradle required)
RUN ./gradlew clean bootJar --no-daemon

FROM eclipse-temurin:11-jre-jammy
ARG JAVA_OPTS=""
ENV JAVA_OPTS=${JAVA_OPTS}

# Copy built artifact from the builder stage
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]