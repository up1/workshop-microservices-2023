FROM node:18-alpine
WORKDIR /app
COPY package.json package.json
RUN npm install
COPY . .
CMD [ "node", "service.js" ]