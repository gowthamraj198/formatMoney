In Local  (non-docker env)
-
After cloning the repo,
Build using the below commands:
- `mvn clean install`
- `mvn clean package`

Bring the service up by executing the command
- `mvn spring-boot:run`

Run api tests by running the command 
- `mvn test -Papi-tests`


In Docker env
-

To build the docker image
- `docker-compose build`

The above command will run all the unit test cases and build the project.

To attach the image to the container and bring the change password service up
- `docker-compose up`

Run api tests by running the command inside a container
- `mvn test -Papi-tests`

To Test from postman
-

Once the service is up Hit the uri from postman or any service testing tool
- `http://localhost:7238/getmoney?money=23`  [Post Request]

Tests
-

To run jmeter tests
- `mvn -DThreadCount=1000 -DTimeOut=3 verify -Papi-performace-tests` 

To run selenium end to end tests
- `mvn test -Pui-tests -Dtest_for=mobile -Dbrowser=firefox` [in firefox with reduced screensize]

- `mvn test -Pui-tests -Dtest_for=web -Dbrowser=firefox` [in firefox with actual screensize]

- `mvn test -Pui-tests -Dtest_for=mobile -Dbrowser=phontom` [in headless browser with reduced screensize]

- `mvn test -Pui-tests -Dtest_for=web -Dbrowser=phontom` [in headless browser with actual screensize]

To run visual regression tests
- `npm install`
- `npm test   -vrt`

To run visual regression tests
- `npm install`
- `npm start`



**Note:**


