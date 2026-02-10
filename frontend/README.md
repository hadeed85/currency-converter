# frontend

A very simple Vue UI for the currency converter backend app.

It has the graphQL client's memory cache.

Runs graphql-codegen to generate types from common schema in the backend.

## Project Setup

```sh
yarn
```

### Compile and Hot-Reload for Development

```sh
yarn dev
```

### Type-Check, Compile and Minify

```sh
yarn build
```

### Lint

```sh
yarn lint
```

### Format

```sh
yarn format
```

### Run with Docker:
Run the following in the root of the project
```
docker compose up --build frontend --no-deps
```