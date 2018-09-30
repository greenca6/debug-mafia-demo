FROM openjdk:8 as BUILD_IMAGE
WORKDIR /root/app

COPY ./gradle ./gradle
COPY ./gradlew .

RUN ./gradlew

COPY settings.gradle .
COPY build.gradle .

COPY . .
RUN ./gradlew clean build -x test

FROM openjdk:8
COPY --from=BUILD_IMAGE /root/app/build/libs/*.jar .
EXPOSE 8080

# following actually runs the server.  Note a few things.
# we are setting the heap size using Xmx.  Also, the jmxremote
# lines are so we can connect to our running JVM and profile
# it using tools like jconsole.  We've opened up port 9010
# for that purpose.

CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n \
    -Xmx200m \
    -Dcom.sun.management.jmxremote \
    -Dcom.sun.management.jmxremote.port=9010 \
    -Dcom.sun.management.jmxremote.local.only=false \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Dcom.sun.management.jmxremote.ssl=false \
    -jar *.jar
