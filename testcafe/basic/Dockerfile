FROM testcafe/testcafe

WORKDIR /app

RUN npm i testcafe-reporter-saucelabs

ADD tests /app/tests/
ADD .testcaferc.js /app/
