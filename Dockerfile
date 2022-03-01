##### BUILD FRONTEND APP #####
FROM node:16 as frontend-build

WORKDIR /usr/app

COPY ./frontend/package*.json ./
RUN npm install

COPY frontend/public ./public
COPY frontend/src ./src

RUN npm run build

##### BUILD BACKEND APP #####
FROM adoptopenjdk:11 as backend-build

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'

WORKDIR /usr/app

COPY ./backend/pom.xml ./
COPY ./backend/mvnw ./
COPY ./backend/.mvn ./.mvn
COPY ./backend/src ./src

COPY --from=frontend-build /usr/app/build ./src/main/resources/META-INF/resources

RUN ./mvnw package

##### RUN APP #####
FROM adoptopenjdk:11

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'

COPY --from=backend-build --chown=185 /usr/app/target/quarkus-app/lib/ /deployments/lib/
COPY --from=backend-build --chown=185 /usr/app/target/quarkus-app/*.jar /deployments/
COPY --from=backend-build --chown=185 /usr/app/target/quarkus-app/app/ /deployments/app/
COPY --from=backend-build --chown=185 /usr/app/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV AB_JOLOKIA_OFF=""
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"


ENTRYPOINT java -jar /deployments/quarkus-run.jar

