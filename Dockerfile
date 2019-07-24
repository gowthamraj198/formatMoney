FROM java:7
FROM maven
RUN mkdir -p /usr/src/app1
WORKDIR /usr/src/app1
COPY . /usr/src/app1/
RUN mvn clean install