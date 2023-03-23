docker run --name myPostgresDb -p 5455:5432 -e POSTGRES_USER=postgresuser -e POSTGRES_PASSWORD=postgrespw -e POSTGRES_DB=todo_list -d postgres
