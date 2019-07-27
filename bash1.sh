nohup mvn spring-boot:run &
sleep 20
mvn test -Papi-tests
mvn -DThreadCount=1000 -DTimeOut=3 verify -Papi-performace-tests

kill -9 $(lsof -t -i :7338 -s tcp:LISTEN)
exit