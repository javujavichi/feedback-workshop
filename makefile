# Makefile for setting up your environment and building the project

# Define variables
JAVA_VERSION = 11
GRADLE_VERSION = 6

# Define targets
.PHONY: setup clean build

setup:
	# Install Homebrew (if not installed)
	/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
	
	# Install Java 11
	brew install openjdk@$(JAVA_VERSION)
	
	# Set JAVA_HOME environment variable
	echo 'export JAVA_HOME=$$(/usr/libexec/java_home -v $(JAVA_VERSION))' >> ~/.bash_profile
	source ~/.bash_profile
	
	# Install Gradle 6
	brew install gradle@$(GRADLE_VERSION)
	
	# Congrats!
	@echo "Setup completed. You can now run 'make build' to build the project."

clean:
	# Clean build
	gradle clean

build:
	# Run clean build
	gradle build
