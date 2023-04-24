FROM node:18

WORKDIR /app

ADD package.json /app/
ADD package-lock.json /app/

RUN npm ci

ADD test /app/test/
ADD tsconfig.json /app/tsconfig.json

RUN mkdir -p /app/artifacts/screenshots

ENTRYPOINT ["npm", "run", "test.saucelabs"]
