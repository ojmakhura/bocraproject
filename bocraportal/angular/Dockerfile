# # Stage 1
# FROM node:18-alpine as build-step

# RUN mkdir -p /app
# WORKDIR /app

# COPY ./bocraportal/angular/target/bocraportal .
# RUN ls -l
# RUN 
# RUN npm install
# RUN npm run build --configuration=production

# Stage 2
FROM nginx:alpine
COPY ./bocraportal/angular/target/bocraportal/dist/* /usr/share/nginx/html/
COPY ./bocraportal/angular/target/bocraportal/dist/assets /usr/share/nginx/html/assets
RUN ls -l /usr/share/nginx/html/*
## COPY --from=build-step /app/dist/ /usr/share/nginx/html
