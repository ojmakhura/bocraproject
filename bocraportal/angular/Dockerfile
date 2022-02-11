# Stage 1
FROM node:17-alpine as build-step

RUN mkdir -p /app
WORKDIR /app

COPY ./bocraportal/angular/package.json .
RUN npm install
COPY ./bocraportal/angular ./
RUN npm run build --prod

# Stage 2
FROM nginx:1.21.6-alpine
COPY --from=build-step /app/dist/ /usr/share/nginx/html
