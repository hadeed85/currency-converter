# currency-converter

This mono repo has two apps that together calculate and show the results for a currency conversion given a source and target currency for a given amount.

The frontend is a simple Vue app that works with a simple graphQL backend service.

You may read the README.md files for the individual apps here:

- [frontend](./frontend/README.md)
- [backend](./backend/README.md)

**Important:** This feature requires the SWOP API-KEY. Please get yours by registering here https://swop.cx/account/dashboard
before proceeding.

Use the environment variable `API_KEY` in Docker Compose to insert your key. Alternatively set it in your env file. 

You can now run the app with docker together or one by one. The steps are below.

## Run everything with Docker
Run the following in the root of the project
```
docker-compose up --build 
```
Then go here: http://localhost:5175

## Links when the whole stack is running
- Frontend URL: http://localhost:5175
- Graphiql playground URL: http://localhost:8080/graphiql
- Backend URL: http://localhost:8080/graphql

You can run all backend commands like so or via your IDE with the help of the env file..
```
mvn -f backend/ mn:run -DAPI_KEY="<INSERT_KEY_HERE>"
```

## Install frontend deps

```sh
yarn --cwd frontend
```

## Build and run frontend
```sh
yarn --cwd frontend dev
```

## Build backend
```sh
mvn -f backend/pom.xml clean package
```

## Run backend
```sh
mvn -f backend/pom.xml mn:run
```



