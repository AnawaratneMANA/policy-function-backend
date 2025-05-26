# Stage 1: Builder with Java 24 and Gradle 8.14
FROM eclipse-temurin:24-jdk AS builder

# Install Gradle 8.13 manually
ENV GRADLE_VERSION=8.14
ENV GRADLE_HOME=/opt/gradle
ENV PATH=${GRADLE_HOME}/bin:${PATH}

RUN apt-get update && apt-get install -y wget unzip \
  && wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
  && mkdir -p /opt/gradle \
  && unzip gradle-${GRADLE_VERSION}-bin.zip -d /opt/gradle \
  && rm gradle-${GRADLE_VERSION}-bin.zip

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Run Gradle build
RUN /opt/gradle/gradle-${GRADLE_VERSION}/bin/gradle clean build -x test

# Stage 2: Runtime container with Java 24 JRE only
FROM eclipse-temurin:24-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
