
mvn flyway:migrate -D flyway.configFiles="C:\Users\natas\Documents\diploma\user-service\src\main\resources\flyway.conf"
mvn flyway:migrate -pl document-service -D flyway.configFiles="/src/main/resources/flyway.conf"
