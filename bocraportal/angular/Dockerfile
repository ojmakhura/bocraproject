# Stage 1
FROM node:10-alpine as build-step

RUN mkdir -p /app
WORKDIR /app

COPY ./coronome-web/package.json .
RUN npm install
COPY ./coronome-web ./
RUN npm run build --prod

# Stage 2
FROM nginx:1.17.1-alpine
COPY --from=build-step /app/dist/ /usr/share/nginx/html
