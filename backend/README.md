# backend

## Architecture

This app providers a graphQL endpoint that uses the SWOP REST endpoint https://swop.cx/documentation/rest#endpoint 
to convert an amount from a source currency to a target currency.

After running the app, please go here: http://localhost:8080/graphiql to run the graphiQL sandbox.

gql sandbox is here: http://localhost:8080/graphiql
gql app is here: http://localhost:8080/graphql

Note: Please register at swop.cx and use your own API key. Remember that for the developer licence, the base currency is limited so you will only be able to use EUR as the base currency.

## How to run locally

**Important:** This feature requires the SWOP API-KEY. Please get yours by registering here https://swop.cx/account/dashboard
before proceeding.

Use the environment variable `API_KEY` in Docker Compose to insert your key. Alternatively set it in your env file. You can run it like so or via your IDE.

You can run all backend commands like so or via your IDE with the help of the env file..

```
mvn mn:run -DAPI_KEY="<INSERT_KEY_HERE>"
```

### Build backend
```sh
mvn -f backend/pom.xml clean package
```

### Run backend
```sh
mvn -f backend/pom.xml mn:run
```

### Run with Docker:
Run the following in the root of the project
```
docker compose up --build backend --no-deps
```

###  Format code
```
mvn compile -Pformat
```

### Example 

POST using: http://localhost:8080/graphiql

```graphql
{
  rate(sourceCurrency: EUR, targetCurrency: GBP, monetaryValue: 1) {
    monetaryValue
    sourceCurrency
    targetCurrency
  }
}
```
We get the following response for the above query

```
{
  "data": {
    "rate": {
      "monetaryValue": "£0.87",
      "sourceCurrency": "EUR",
      "targetCurrency": "GBP"
    }
  }
}
```

## The app utilises the following

* Micronaut 
* Java
* Maven
* GraphQL
* GraphiQL
* SWOP
* google-java-format
* Logback


## Improvements to be made

* Put anything left hardcoded into the config file
* e2e tests
* ENV variables for config file for Prod deployment
* Log file configuration
* Deactivate introspection and sandbox in production
* Caffeine cache or redis for server side cache

### Security improvements to be made

* Run an OWASP based dependency checker against CVEs and upgrade and/or exclude transitive dependencies etc accordingly
* Endpoint security needs to be there. Standard JWT token should be sent in the request to every endpoint and test against.
  an application.conf file with a map containing roles and endpoints. Match the JWT token role with the one in the map for the relevant endpoint.
  Alternatively, we build our middleware/custom directives that will introduce role/authorisation/authentication/security annotations, e.g. Node and NestJS has Passport strategy, other frameworks have similar things.
* We can use Auth0 or OAuth for token generation if a bespoke solution is a maintenance cost, and outsource roles and use "custom claims" with these 3rd party providers for more bespoke needs.
* Caching tokens in memory etc. 
* Enable TLS support for the server, but ideally terminate at envoy or similar, and of course SSL certs client side.
* Firewalls, Network security.
* Also, sensible coding.

### Health check improvements to be made
* Aa separate endpoint for checking the health of the service, i.e. whether it is running or not. Can be used with or without Kubernetes and depending on what you are using
  you generally need a health service endpoint with Kubernetes to check the health of a service running on a Node. 




