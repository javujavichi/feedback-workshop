# Devcontainer Dockerfile for Codespaces / VS Code Remote Containers
# Base image provides OpenJDK 11 and common tooling for Java development
FROM mcr.microsoft.com/vscode/devcontainers/base:0-ubuntu-22.04

# Install OpenJDK 11 and common utilities useful for the workshop
RUN apt-get update \
  && apt-get install -y --no-install-recommends \
    openjdk-11-jdk \
    git \
    unzip \
    wget \
    curl \
  && rm -rf /var/lib/apt/lists/*

# Set JAVA_HOME for tools that rely on it
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"

# Ensure the default workspace folder exists (Codespaces will bind-mount the repo)
WORKDIR /workspace

# Keep the non-root 'vscode' user that the base image provides
USER vscode

# Provide a helpful message when the container starts (optional)
CMD ["sleep", "infinity"]
