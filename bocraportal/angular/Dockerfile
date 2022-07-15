# Stage 1
FROM node:18-alpine as build-step

RUN mkdir -p /app
WORKDIR /app

COPY ./bocraportal/angular/target/bocraportal .
RUN npm install
RUN npm run build --configuration=production

# Stage 2
FROM nginx:alpine
COPY --from=build-step /app/dist/ /usr/share/nginx/html
