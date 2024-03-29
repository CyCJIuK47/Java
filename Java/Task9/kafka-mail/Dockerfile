FROM openjdk:17-alpine AS buildArtifact

ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME

ADD .mvn $HOME/.mvn
ADD mvnw $HOME
ADD src $HOME/src
ADD pom.xml $HOME

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine AS layeredBuilder

ENV HOME=/usr/app
WORKDIR $HOME

COPY --from=buildArtifact $HOME/target/*.jar application.jar
RUN java -Djarmode=layertools -jar --enable-preview application.jar extract

FROM openjdk:17-alpine

ENV HOME=/usr/app
WORKDIR $HOME

COPY --from=layeredBuilder $HOME/dependencies/ ./
COPY --from=layeredBuilder $HOME/snapshot-dependencies/ ./
COPY --from=layeredBuilder $HOME/spring-boot-loader/ ./
COPY --from=layeredBuilder $HOME/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]