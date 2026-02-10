FROM node:24-alpine AS build

WORKDIR /app/frontend

COPY frontend/package.json frontend/yarn.lock ./
RUN yarn install --frozen-lockfile

COPY frontend/codegen.yml ./
COPY frontend/env.d.ts ./
COPY frontend/vite.config.ts ./
COPY frontend/tsconfig.node.json ./
COPY frontend/tsconfig.json ./
COPY frontend/tsconfig.app.json ./
COPY frontend/src ./src
COPY frontend/index.html ./
COPY backend/src/main/resources/schema.graphqls /app/backend/src/main/resources/schema.graphqls

RUN yarn build

FROM nginxinc/nginx-unprivileged:stable-alpine-slim as nginx

COPY --from=build /app/frontend/dist /usr/share/nginx/html
COPY frontend/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 5175