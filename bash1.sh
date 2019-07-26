nohup mvn spring-boot:run &
kill -9 $(lsof -t -i :7338 -s tcp:LISTEN)