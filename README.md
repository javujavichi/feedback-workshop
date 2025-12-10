# feedback-workshop
Workshop to play with ArchUnit and enforce your architecture using Java Tests

## Instructions

This project has a few branches to play and work with arch unit, the main idea is to refactor each *chaos branch* using the available architecture tests. You can always add more tests or refactor the application in order to pass a test.

### Activities in chaos branchs

This section list different activities for the repository. Even when the code is not perfect it pretends to be treated as a real life project that was created by a lot of people collaborating together and now need to improve the architecture and organization of the project before adding a new domain.

#### *add-test-chaos*
This branch does not have any tests, helps the user to start with arch unit basic rules

#### *refactor-organization-chaos*
This branch does have the test, but also is unorganized and to solve the mistery you need to place the files in the correct folders to pass the tests

#### ...more branches to come...


## Set up your environment

There are two ways to run this project: **using Docker (easiest)** or **installing Java locally (for development)**.

---

### Option 1: Docker (Recommended for Workshop Participants)

**No Java installation required!** Just install Docker and you're ready to go.

#### Prerequisites
- Install [Docker Desktop](https://www.docker.com/products/docker-desktop)
  - **macOS/Windows**: Download and install Docker Desktop
  - **Linux**: Install Docker Engine via your package manager

#### Run the application
```bash
# Build and start the application
docker-compose up --build

# Or run detached (in background)
docker-compose up --build -d

# Stop the application
docker-compose down
```

The application will be available at `http://localhost:8080`

**Pros:**
- ✅ No Java installation needed
- ✅ Works identically on all operating systems
- ✅ Isolated environment (no conflicts)
- ✅ Single command to get started

**Cons:**
- ⚠️ Requires Docker Desktop
- ⚠️ Slightly slower startup

---

### Option 2: Local Java Installation (Recommended for Development)

Install Java directly on your machine for faster iteration and debugging.

#### Quick Setup with Makefile

```bash
# See all available commands
make help

# Auto-detect your OS and install dependencies
make setup
```

The `make setup` command will:
- **macOS**: Install Homebrew (if needed) and OpenJDK 11
- **Linux**: Use apt/yum to install OpenJDK 11
- **Windows**: Display installation instructions (Chocolatey, manual, or Docker)

After setup, apply environment changes:
```bash
# macOS/zsh
source ~/.zshrc

# Linux/bash
source ~/.bashrc
```

#### Run the application
```bash
make build              # Build the project
make run                # Start the application
make test               # Run all tests
make test-architecture  # Run architecture tests only
make clean              # Clean build artifacts
```

Or use Gradle directly:
```bash
./gradlew bootRun       # Start the application
./gradlew build         # Build the project
./gradlew test          # Run tests
```

**Pros:**
- ✅ Faster startup and iteration
- ✅ Better for active development
- ✅ Native debugging support

**Cons:**
- ⚠️ Requires Java 11 installation
- ⚠️ Different setup per OS

#### Manual Setup (Alternative)
If you prefer manual installation, you can use [SDKMAN](https://sdkman.io/):

```bash
# Install SDKMAN
curl -s "https://get.sdkman.io" | bash

# Install Java 11 and Gradle 6.4
sdk install java 11.0.12-open
sdk install gradle 6.4
```

---

### For VSCode development
#### Recommended Extensions

- Debugger for Java
- Extension Pack for Java
- Gradle for Java
- Language Support for Java(TM) by Red Hat
- Project Manager for Java
- Test Runner for Java

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

## Codespaces

This repository includes a Codespaces devcontainer so contributors can run the app without installing Java or Gradle locally. The devcontainer uses `.devcontainer/Dockerfile` and forwards port `8080`.

Quick start (GitHub Codespaces)

- Open the repository in a Codespace: **Code → Codespaces → New codespace**.
- Wait for the Codespace container to finish building (the `postCreateCommand` will run `./gradlew --version`).
- In the Codespace terminal run:

```bash
chmod +x ./gradlew
./gradlew bootRun
```

- Open the **Ports** panel, forward port `8080`, then click **Open in Browser** to view the app.

Troubleshooting

- If the app doesn't start, try building first:

```bash
./gradlew clean build
```

- If you need to run locally (not in Codespaces), ensure **Java 11** is installed and `JAVA_HOME` points to it.

Notes

- Codespaces uses the dev-specific `.devcontainer/Dockerfile`. The root `Dockerfile` in the repository is a production-style multi-stage image (used to build a runtime image). You can remove or rename it if you don't need a production image in this repo.

Run with Docker (no Java/Gradle install required)

Build and run with Docker directly:

```bash
# build the image
docker build -t feedback-workshop:latest .

# run the container and forward port 8080
docker run --rm -p 8080:8080 feedback-workshop:latest
```

Or use docker-compose (preferred for contributors):

```bash
# build and run in foreground
docker-compose up --build

# or run detached
docker-compose up --build -d

# stop and remove
docker-compose down
```

If you publish the image to a registry (GitHub Container Registry or Docker Hub), contributors can skip the build step and run the image directly:

```bash
docker run --rm -p 8080:8080 ghcr.io/<OWNER>/feedback-workshop:latest
```

