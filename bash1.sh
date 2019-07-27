nohup mvn spring-boot:run &
sleep 20
mvn test -Papi-tests

#kill -9 $(lsof -t -i :7338 -s tcp:LISTEN)
#exit