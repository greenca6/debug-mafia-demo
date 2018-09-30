FROM openjdk:8 as BUILD_IMAGE

WORKDIR /root/app

COPY . .

EXPOSE 8080

CMD ./gradlew bootRun
