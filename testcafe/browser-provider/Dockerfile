FROM node:20-alpine3.18

RUN apk add gcompat

WORKDIR /app

ADD package.json /app/
ADD package-lock.json /app/

RUN npm i

ADD tests /app/tests/

ENTRYPOINT ["npx", "testcafe"]
