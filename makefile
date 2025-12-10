# Makefile for setting up your environment and building the project
# Works on macOS, Linux, and Windows (with WSL or Git Bash)

# Detect operating system
UNAME_S := $(shell uname -s 2>/dev/null || echo Windows)

# Define variables
JAVA_VERSION = 11
GRADLE_VERSION = 6.4

# Define targets
.PHONY: help setup setup-mac setup-linux setup-windows clean build run test

help:
	@echo "Available commands:"
	@echo "  make setup        - Auto-detect OS and install dependencies"
	@echo "  make setup-mac    - Install dependencies on macOS using Homebrew"
	@echo "  make setup-linux  - Install dependencies on Linux using apt/yum"
	@echo "  make setup-windows - Show instructions for Windows"
	@echo "  make build        - Build the project"
	@echo "  make run          - Run the application"
	@echo "  make test         - Run tests"
	@echo "  make clean        - Clean build artifacts"

setup:
ifeq ($(UNAME_S),Darwin)
	@echo "Detected macOS, running setup-mac..."
	@$(MAKE) setup-mac
else ifeq ($(UNAME_S),Linux)
	@echo "Detected Linux, running setup-linux..."
	@$(MAKE) setup-linux
else
	@echo "Detected Windows, showing setup instructions..."
	@$(MAKE) setup-windows
endif

setup-mac:
	@echo "Setting up environment for macOS..."
	# Check if Homebrew is installed
	@command -v brew >/dev/null 2>&1 || { \
		echo "Installing Homebrew..."; \
		/bin/bash -c "$$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"; \
	}
	
	# Install Java 11 if not present
	@command -v java >/dev/null 2>&1 && java -version 2>&1 | grep -q "11\." || { \
		echo "Installing OpenJDK 11..."; \
		brew install openjdk@11; \
		echo 'export PATH="/usr/local/opt/openjdk@11/bin:$$PATH"' >> ~/.zshrc; \
		echo 'export JAVA_HOME=$$(/usr/libexec/java_home -v 11)' >> ~/.zshrc; \
	}
	
	@echo ""
	@echo "✅ Setup completed for macOS!"
	@echo "⚠️  Run 'source ~/.zshrc' to apply environment changes"
	@echo "Then run 'make build' to build the project."

setup-linux:
	@echo "Setting up environment for Linux..."
	# Detect package manager
	@if command -v apt-get >/dev/null 2>&1; then \
		echo "Using apt package manager..."; \
		sudo apt-get update; \
		sudo apt-get install -y openjdk-11-jdk; \
	elif command -v yum >/dev/null 2>&1; then \
		echo "Using yum package manager..."; \
		sudo yum install -y java-11-openjdk-devel; \
	else \
		echo "⚠️  Could not detect package manager. Please install OpenJDK 11 manually."; \
		exit 1; \
	fi
	
	# Set JAVA_HOME
	@echo 'export JAVA_HOME=$$(dirname $$(dirname $$(readlink -f $$(which java))))' >> ~/.bashrc
	
	@echo ""
	@echo "✅ Setup completed for Linux!"
	@echo "⚠️  Run 'source ~/.bashrc' to apply environment changes"
	@echo "Then run 'make build' to build the project."

setup-windows:
	@echo ""
	@echo "==================================================================="
	@echo "Windows Setup Instructions"
	@echo "==================================================================="
	@echo ""
	@echo "Option 1: Using Chocolatey (recommended)"
	@echo "  1. Install Chocolatey: https://chocolatey.org/install"
	@echo "  2. Run in PowerShell (as Administrator):"
	@echo "     choco install openjdk11 -y"
	@echo ""
	@echo "Option 2: Manual Installation"
	@echo "  1. Download OpenJDK 11 from:"
	@echo "     https://adoptium.net/temurin/releases/?version=11"
	@echo "  2. Install and set JAVA_HOME environment variable"
	@echo "  3. Add JAVA_HOME\\bin to PATH"
	@echo ""
	@echo "Option 3: Use Docker (easiest - no local Java needed)"
	@echo "  1. Install Docker Desktop: https://www.docker.com/products/docker-desktop"
	@echo "  2. Run: docker-compose up --build"
	@echo ""
	@echo "After setup, run 'make build' to verify installation."
	@echo "==================================================================="

clean:
	@echo "Cleaning build artifacts..."
	./gradlew clean

build:
	@echo "Building the project..."
	./gradlew build

run:
	@echo "Starting the application..."
	./gradlew bootRun

test:
	@echo "Running tests..."
	./gradlew test

test-architecture:
	@echo "Running architecture tests..."
	./gradlew test --tests '*architecture*'
