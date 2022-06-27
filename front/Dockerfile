FROM node:14.16.0-alpine3.11 as build
WORKDIR /app

RUN npm install -g @angular/cli


COPY ./package.json .
RUN npm install

COPY . .
RUN npm start

