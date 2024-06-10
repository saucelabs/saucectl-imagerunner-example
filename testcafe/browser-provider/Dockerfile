FROM node:20-alpine3.18

RUN apk add gcompat

WORKDIR /app

ADD package.json /app/
ADD package-lock.json /app/
ADD https://saucelabs.com/downloads/sc-4.9.2-linux.tar.gz /tmp/
RUN tar -xzf /tmp/sc-4.9.2-linux.tar.gz -C /app/
ENV SAUCE_CONNECT_OVERRIDES_PATH=/app/sc_overrides.json

RUN npm i

ADD tests /app/tests/
ADD sc_overrides.json /app/

ENTRYPOINT ["npx", "testcafe"]
