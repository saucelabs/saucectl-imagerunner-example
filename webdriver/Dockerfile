FROM node:18

RUN mkdir /app/
WORKDIR /app

ADD package.json /app/
ADD package-lock.json /app/

RUN npm ci

ADD test /app/test/
ADD tsconfig.json /app/tsconfig.json

ENTRYPOINT ["npm", "run", "test.saucelabs"]
