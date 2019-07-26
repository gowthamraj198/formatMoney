nohup java -jar FormatMoney-1.0-SNAPSHOT.jar &
kill -9 $(lsof -t -i :7338 -s tcp:LISTEN)