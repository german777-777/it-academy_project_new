ARG MAVEN_BUILD_IMAGE=maven:3.8.3-openjdk-17
ARG JDK_RUNTIME_IMAGE=openjdk:17

FROM ${MAVEN_BUILD_IMAGE} as dependencies
COPY pom.xml ./
RUN mvn -B dependency:go-offline

FROM dependencies as build
COPY src ./src
RUN mvn -B clean package

FROM ${JDK_RUNTIME_IMAGE}
ARG JAR=target/it-academy_project-0.0.1-SNAPSHOT.jar
COPY --from=build ${JAR} project.jar
RUN echo "#!/bin/bash" >> up.sh
RUN echo "sleep \$ENTRY_DELAY" >> up.sh
RUN echo "java -jar app.jar" >> up.sh
RUN chmod +x up.sh
ENTRYPOINT ["/up.sh"]