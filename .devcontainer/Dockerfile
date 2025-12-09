# Devcontainer Dockerfile for Codespaces / VS Code Remote Containers
# Base image provides OpenJDK 11 and common tooling for Java development
FROM mcr.microsoft.com/vscode/devcontainers/java:0-11

# Install common utilities useful for the workshop
RUN apt-get update \
  && apt-get install -y --no-install-recommends \
    git \
    unzip \
    wget \
    curl \
  && rm -rf /var/lib/apt/lists/*

# Ensure the default workspace folder exists (Codespaces will bind-mount the repo)
WORKDIR /workspace

# Keep the non-root 'vscode' user that the base image provides
USER vscode

# Provide a helpful message when the container starts (optional)
CMD ["sleep", "infinity"]
