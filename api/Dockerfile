FROM openjdk:8 as BUILD_IMAGE

WORKDIR /root/app

COPY . .

RUN ./gradlew build

FROM openjdk:8

COPY --from=BUILD_IMAGE /root/app/build/libs/*.jar .

CMD java -Xmx50m -jar *.jar
