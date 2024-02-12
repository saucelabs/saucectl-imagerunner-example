FROM testcafe/testcafe:3.5.0

WORKDIR /app

RUN npm i testcafe-reporter-saucelabs

ADD tests /app/tests/
ADD .testcaferc.js /app/
