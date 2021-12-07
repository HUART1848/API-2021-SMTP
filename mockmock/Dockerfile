FROM openjdk:11

WORKDIR /

COPY release/MockMock.jar .

EXPOSE 2525 8282

CMD java -jar MockMock.jar -p 2525 -h 8282
