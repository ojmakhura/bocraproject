# # Stage 1
FROM node:18-alpine as build-step

RUN mkdir -p /app
WORKDIR /app

COPY ./bocraportal/angular/target/bocraportal .
COPY ./.env .
RUN npm install
RUN chmod 755 .env && . ./.env && npm run build --configuration=production

# Stage 2
FROM nginx:alpine
COPY --from=build-step /app/dist/ /usr/share/nginx/html
